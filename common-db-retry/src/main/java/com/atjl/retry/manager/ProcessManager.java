package com.atjl.retry.manager;

import com.atjl.retry.api.DataContext;
import com.atjl.retry.api.domain.RetryData;
import com.atjl.retry.api.option.PageOption;
import com.atjl.retry.domain.RetryInnerConstant;
import com.atjl.retry.domain.RetryServiceItem;
import com.atjl.retry.service.AfterDefaultService;
import com.atjl.retry.util.OptionUtil;
import com.atjl.util.common.domain.ExcepResDto;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.reflect.ReflectGetUtil;
import com.atjl.util.reflect.ReflectSetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ProcessManager {
    private static final Logger logger = LoggerFactory.getLogger(ProcessManager.class);
    @Resource
    GetDataManager getDataManager;
    @Resource
    InstanceRetryManager instanceRetryManager;
    @Resource
    AfterDefaultService afterDefaultService;
    @Resource
    AfterManager afterManager;

    /**
     * 处理 一个服务 内 一条数据 的重试
     *
     * @param retryServiceItem 重试服务
     * @param dataContext      上下文数据
     */
    public void processItem(RetryServiceItem retryServiceItem, DataContext dataContext) {
        String failReason = null;

        //dataContext 内放入 option，后续的 框架自带的AfterService使用
        PageOption pageOpt = retryServiceItem.getPageOption();
        ReflectSetUtil.setterForce(dataContext, RetryInnerConstant.FIELD_OPTION, pageOpt);

        //从dataContext取出 retryData
        RetryData retryData = (RetryData) ReflectGetUtil.getterForce(dataContext, RetryInnerConstant.FIELD_RETRY_DATA);

        /**
         * 是否到达时间
         */
        if (retryServiceItem.getRetryOption() != null) {
            if (!OptionUtil.isTimeUp(retryServiceItem.getRetryOption(), retryData)) {
                return;
            }

        }

        ExcepResDto retryResp;
        if (retryServiceItem.getRetryInstanceOption() != null) {
            //需要立即重试的
            retryResp = instanceRetryManager.instanceRetry(retryServiceItem.getRetryInstanceOption(), retryServiceItem.getRetryService(), dataContext);
        } else {
            //无需立即重试的
            retryResp = instanceRetryManager.noRetry(retryServiceItem.getRetryService(), dataContext);
        }

        if (!retryResp.isRes()) {
            if (retryResp.getE() != null) {
                failReason = retryResp.getE().getMessage();
            }
            dataContext.setFailReason(failReason);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("retry execute res {}", JSONFastJsonUtil.objectToJson(retryResp));
        }

        /**
         * 后置操作
         */
        afterManager.after(retryResp.isRes(), retryServiceItem, dataContext);
    }


}
