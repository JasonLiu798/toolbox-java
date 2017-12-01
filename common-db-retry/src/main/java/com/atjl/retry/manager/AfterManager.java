package com.atjl.retry.manager;

import com.atjl.retry.api.AfterService;
import com.atjl.retry.api.DataContext;
import com.atjl.retry.api.option.PageOption;
import com.atjl.retry.api.option.RetryAfterType;
import com.atjl.retry.domain.RetryServiceItem;
import com.atjl.retry.service.AfterDefaultService;
import com.atjl.retry.util.OptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author jasonLiu
 */
@Component
public class AfterManager {
    private static final Logger logger = LoggerFactory.getLogger(AfterManager.class);

    @Resource
    private AfterDefaultService retryAfterDefaultService;

    /**
     * @param exeRes      成功/失败
     * @param dataContext 上下文
     */
    public void after(boolean exeRes, RetryServiceItem retryServiceItem, DataContext dataContext) {
        if (retryServiceItem.getPageOption().getAfterType() == RetryAfterType.NONE) {
            logger.debug("no after service");
            return;
        }
        try {
            if (exeRes) {
                //成功
                /**
                 * 所有可能顺序
                 * cust
                 * default
                 * cust -> defualt
                 * default -> cust
                 */
                succ(retryServiceItem.getPageOption(), dataContext, retryServiceItem.getAfterService());
            } else {
                //失败
                fail(retryServiceItem.getPageOption(), dataContext, retryServiceItem.getAfterService());
            }
        } catch (Exception e) {
            logger.error("{}", e);
        }
    }

    private void fail(PageOption opt, DataContext dataContext, AfterService retryAfterService) {
        if (OptionUtil.hasSequential(opt)) {
            /**
             * cust -> defualt
             * default -> cust
             */
            if (OptionUtil.isForward(opt)) {
                retryAfterDefaultService.fail(dataContext);
                retryAfterService.fail(dataContext);
            } else {
                retryAfterService.fail(dataContext);
                retryAfterDefaultService.fail(dataContext);
            }
        } else {
            /**
             * cust/default
             */
            if (OptionUtil.isCustomAfter(opt)) {
                retryAfterService.fail(dataContext);
            } else {
                retryAfterDefaultService.fail(dataContext);
            }
        }
    }

    private void succ(PageOption opt, DataContext dataContext, AfterService retryAfterService) {
        if (OptionUtil.hasSequential(opt)) {
            /**
             * cust -> defualt
             * default -> cust
             */
            if (OptionUtil.isForward(opt)) {
                retryAfterDefaultService.succ(dataContext);
                retryAfterService.succ(dataContext);
            } else {
                retryAfterService.succ(dataContext);
                retryAfterDefaultService.succ(dataContext);
            }
        } else {
            /**
             * cust/default
             */
            if (OptionUtil.isCustomAfter(opt)) {
                retryAfterService.succ(dataContext);
            } else {
                retryAfterDefaultService.succ(dataContext);
            }
        }
    }
}
