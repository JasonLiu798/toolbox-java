package com.atjl.retry.manager;

import com.atjl.retry.api.DataContext;
import com.atjl.retry.api.RetryService;
import com.atjl.retry.api.option.InitOption;
import com.atjl.util.common.InstanceRetryUtil;
import com.atjl.util.common.domain.ExcepResDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class InstanceRetryManager {
    private static final Logger logger = LoggerFactory.getLogger(InstanceRetryManager.class);

    /**
     * 无需立即重试
     *
     * @param retryService
     * @param data
     * @return
     */
    public ExcepResDto noRetry(RetryService retryService, DataContext data) {
        logger.debug("no instance retry");
        ExcepResDto res = new ExcepResDto();
        try {
            res.setRes(retryService.execute(data));
        } catch (Exception e) {
            logger.debug("{}", e);
            res.setE(e);
        }
        return res;
    }

    /**
     * 需要立即重试
     *
     * @param opt
     * @param retryService
     * @param data
     * @return
     */
    public ExcepResDto instanceRetry(InitOption opt, RetryService retryService, DataContext data) {
        if (logger.isInfoEnabled()) {
            logger.info("instance retry,retry cnt {},wait {}", opt.getInstanceRetryOption().getRetryCount(), opt.getInstanceRetryOption().getWaitMs());
        }
        ExcepResDto retryResp = InstanceRetryUtil.retry(opt.getInstanceRetryOption().getRetryCount(), opt.getInstanceRetryOption().getWaitMs(), () -> retryService.execute(data)
        );
        return retryResp;
    }
}
