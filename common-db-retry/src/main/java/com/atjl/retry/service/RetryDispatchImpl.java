package com.atjl.retry.service;

import com.atjl.retry.api.ExecuteBatchService;
import com.atjl.retry.api.ExecuteService;
import com.atjl.retry.api.GetOptionService;
import com.atjl.retry.api.RetryDispatch;
import com.atjl.retry.api.domain.RetryStatusDto;
import com.atjl.retry.api.exception.RetryInitException;
import com.atjl.retry.api.exception.RetryRegisteException;
import com.atjl.retry.api.option.PageOption;
import com.atjl.retry.api.option.RetryInstanceOption;
import com.atjl.retry.api.option.RetryOption;
import com.atjl.retry.domain.RetryServiceItem;
import com.atjl.retry.manager.GetDataManager;
import com.atjl.retry.manager.OptionManager;
import com.atjl.retry.manager.PageProcessorManager;
import com.atjl.retry.manager.ProcessManager;
import com.atjl.retry.util.RetryCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.reflect.ReflectClassUtil;
import com.atjl.utilex.ApplicationContextHepler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 重试调度
 * 1.服务注册
 * 2.按服务执行
 *
 * @author jasondliu
 */
@Component
public class RetryDispatchImpl implements RetryDispatch {

    private static final Logger logger = LoggerFactory.getLogger(RetryDispatch.class);

    private Map<String, RetryServiceItem> retryServices = new ConcurrentHashMap<>();


    @Resource
    private OptionManager retryOptionManager;
    @Resource
    AfterDefaultService retryAfterServiceBase;
    @Resource
    PageProcessorManager pageProcessManager;
    @Resource
    GetDataManager retryGetManager;
    @Resource
    ProcessManager retryProcessManager;

    /**
     * 注册重试服务
     *
     * @param serviceName spring bean 名，需实现 RetryService接口
     */
    @Override
    public void registe(String serviceName) {
        if (retryServices.containsKey(serviceName)) {
            throw new RetryRegisteException("已经注册了服务，" + serviceName);
        }

        Object serviceObj = ApplicationContextHepler.getBeanByName(serviceName);
        if (serviceObj == null) {
            throw new RetryInitException("获取重试服务为空，服务名" + serviceName);
        }

        if (!ReflectClassUtil.chkAImplementB(serviceObj, GetOptionService.class)) {
            throw new RetryInitException("服务未实现 " + GetOptionService.class.getName() + " 接口，服务名" + serviceName);
        }

        RetryServiceItem retryServiceItem = new RetryServiceItem();

        GetOptionService getOptionService = (GetOptionService) serviceObj;
        PageOption opt = getOptionService.getInitOption();
        if (opt.isBatchProcess()) {
            if (ReflectClassUtil.chkAImplementB(serviceName, ExecuteBatchService.class)) {
                throw new RetryInitException("获取重试服务未实现 " + ExecuteBatchService.class.getName() + " 接口，服务名" + serviceName);
            }
            ExecuteBatchService service = (ExecuteBatchService) serviceObj;
            retryServiceItem.setExecuteBatchService(service);
        } else {
            if (!ReflectClassUtil.chkAImplementB(serviceObj, ExecuteService.class)) {
                throw new RetryInitException("获取重试服务未实现 " + ExecuteService.class.getName() + " 接口，服务名" + serviceName);
            }
            ExecuteService service = (ExecuteService) serviceObj;
            retryServiceItem.setRetryService(service);
        }

        //检查并设置 自定义取数服务
        RetryCheckUtil.customGetData(opt, serviceObj, retryServiceItem);

        //检查并设置 自定义后置服务
        RetryCheckUtil.customAfter(opt, serviceObj, retryServiceItem);

        if (opt instanceof RetryOption) {
            RetryOption ropt = (RetryOption) opt;
            retryOptionManager.checkRetryOption(ropt);
            retryServiceItem.setRetryOption(ropt);
            retryServiceItem.setRetryInstanceOption(ropt);
        } else if (opt instanceof RetryInstanceOption) {
            RetryInstanceOption ropt = (RetryInstanceOption) opt;
            retryServiceItem.setRetryInstanceOption(ropt);
            retryOptionManager.checkRetryInstanceOption(ropt);
        } else {
            retryOptionManager.checkPageOption(opt);
        }
        retryServiceItem.setPageOption(opt);

        retryServices.put(serviceName, retryServiceItem);
    }

    /**
     * 调度接口
     */
    @Override
    public void executeAll() {
        for (Map.Entry<String, RetryServiceItem> entry : retryServices.entrySet()) {
            logger.info("process service {}", entry.getKey());
            pageProcessManager.pageProcess(entry.getValue(),null);
        }
    }

    /**
     * 只调度，指定服务,并可传参
     */
    public void executeService(String service,Object cond) {
        RetryServiceItem retryServiceItem = retryServices.get(service);
        pageProcessManager.pageProcess(retryServiceItem,cond);

    }

    @Override
    public List<RetryStatusDto> getOptions() {
        List<RetryStatusDto> statusMap = new ArrayList<>();
        if (!CollectionUtil.isEmpty(retryServices)) {
            for (Map.Entry<String, RetryServiceItem> entry : retryServices.entrySet()) {
                RetryServiceItem item = entry.getValue();
//                ExecuteService service = item.getRetryService();
                RetryStatusDto statusDto = new RetryStatusDto();
                statusDto.setRetryOption(item.getRetryOption());
                statusDto.setPageOption(item.getPageOption());
                statusDto.setRetryInstanceOption(item.getRetryInstanceOption());
                statusDto.setServiceName(entry.getKey());
                statusMap.add(statusDto);
            }
        }
        return statusMap;
    }

}
