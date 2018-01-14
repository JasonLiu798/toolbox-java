package com.atjl.retry.manager;

import com.atjl.retry.api.option.PageOption;
import com.atjl.retry.domain.RetryServiceItem;
import org.springframework.stereotype.Component;

@Component
public class GeneralPreServiceManager {

    public boolean preService(RetryServiceItem retryServiceItem, PageOption opt) {
        if (opt.isGeneralPreService()) {
            try {
                return retryServiceItem.getGeneralPreService().process();
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
