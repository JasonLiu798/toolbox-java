package com.atjl.retry.service;

import com.atjl.common.constant.CommonConstant;
import com.atjl.retry.api.AfterService;
import com.atjl.retry.api.DataContext;
import com.atjl.retry.api.option.InitOption;
import com.atjl.retry.api.domain.RetryData;
import com.atjl.retry.domain.RetryInnerConstant;
import com.atjl.retry.mapper.biz.RetryMapper;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.reflect.ReflectGetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 默认后置服务
 * 默认执行  更新
 *
 * @author jasondliu
 */
@Component
public class AfterDefaultService implements AfterService {
    private static final Logger logger = LoggerFactory.getLogger(AfterDefaultService.class);

    @Resource
    private RetryMapper retryMapper;

    @Override
    public void succ(DataContext context) {
        RetryData data = (RetryData) ReflectGetUtil.getterForce(context, RetryInnerConstant.FIELD_RETRY_DATA);
        data.setFailReason(null);
        data.setRes(CommonConstant.YES);
        if (logger.isDebugEnabled()) {
            logger.debug("process success {}", JSONFastJsonUtil.objectToJson(data));
        }
        updateRetryRes(context, data);
    }

    @Override
    public void fail(DataContext context) {
        RetryData data = (RetryData) ReflectGetUtil.getterForce(context, RetryInnerConstant.FIELD_RETRY_DATA);
        data.setRes(CommonConstant.NO);
        if (logger.isDebugEnabled()) {
            logger.debug("process fail {}", JSONFastJsonUtil.objectToJson(data));
        }

        updateRetryRes(context, data);
    }

    private void updateRetryRes(DataContext context, RetryData data) {
        InitOption opt = context.getInitOption();
        retryMapper.updateRetryResult(opt, opt.getRetryTabMeta(), data);
    }

}
