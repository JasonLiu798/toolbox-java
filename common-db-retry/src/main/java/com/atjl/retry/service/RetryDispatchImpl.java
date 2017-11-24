package com.atjl.retry.service;

import com.atjl.retry.api.RetryDispatch;
import com.atjl.retry.api.RetryService;
import com.atjl.retry.api.domain.RetryStatusDto;
import com.atjl.retry.api.exception.RetryInitException;
import com.atjl.retry.api.exception.RetryRegisteException;
import com.atjl.retry.api.option.InitOption;
import com.atjl.retry.domain.RetryServiceItem;
import com.atjl.retry.manager.PageProcessorManager;
import com.atjl.retry.manager.GetDataManager;
import com.atjl.retry.manager.OptionManager;
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
    PageProcessorManager retryDispatchManager;
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
        Object serviceObj = ApplicationContextHepler.getBeanByName(serviceName);
        if (serviceObj == null) {
            throw new RetryInitException("获取重试服务为空，服务名" + serviceName);
        }

        if (!ReflectClassUtil.chkAImplementB(serviceObj, RetryService.class)) {
            throw new RetryInitException("获取重试服务未实现RetryService接口，服务名" + serviceName);
        }

        if (retryServices.containsKey(serviceName)) {
            throw new RetryRegisteException("已经注册了服务，" + serviceName);
        }

        RetryServiceItem retryServiceItem = new RetryServiceItem();

        RetryService service = (RetryService) serviceObj;
        retryServiceItem.setRetryService(service);
        InitOption opt = service.getInitOption();

        //检查并设置 自定义取数服务
        RetryCheckUtil.customGetData(opt, serviceObj, retryServiceItem);

        //检查并设置 自定义后置服务
        RetryCheckUtil.customAfter(opt, serviceObj, retryServiceItem);

        //其他参数校验，抛出 校验异常
        retryOptionManager.checkOption(opt);
        retryServiceItem.setInitOption(opt);

        retryServices.put(serviceName, retryServiceItem);
    }

    /**
     * 调度接口
     */
    @Override
    public void timeUp() {
        for (Map.Entry<String, RetryServiceItem> entry : retryServices.entrySet()) {
            logger.info("process service {}", entry.getKey());
            retryDispatchManager.pageProcess(entry.getValue());
        }
    }


    @Override
    public List<RetryStatusDto> getOptions() {
        List<RetryStatusDto> statusMap = new ArrayList<>();
        if (!CollectionUtil.isEmpty(retryServices)) {
            for (Map.Entry<String, RetryServiceItem> entry : retryServices.entrySet()) {
                RetryService service = entry.getValue().getRetryService();
                RetryStatusDto statusDto = new RetryStatusDto();
                statusDto.setInitOption(service.getInitOption());
                statusDto.setServiceName(entry.getKey());
                statusMap.add(statusDto);
            }
        }
        return statusMap;
    }

}
