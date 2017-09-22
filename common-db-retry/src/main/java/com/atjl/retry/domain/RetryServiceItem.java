package com.atjl.retry.domain;


import com.atjl.retry.api.*;
import com.atjl.retry.api.CustomGetDatas;
import com.atjl.retry.api.CustomGetDatasUseIdPage;
import com.atjl.retry.api.option.InitOption;

public class RetryServiceItem {
    /**
     * 主服务，必存在
     */
    private RetryService retryService;
    /**
     * 后置服务
     */
    private AfterService afterService;

    private CustomGetDatas retryServiceCustomGetDatas;
    private CustomGetDatasUseIdPage retryServiceGetDatasUseIdPage;
    private InitOption initOption;

    public AfterService getAfterService() {
        return afterService;
    }

    public CustomGetDatasUseIdPage getRetryServiceGetDatasUseIdPage() {
        return retryServiceGetDatasUseIdPage;
    }


    public void setRetryServiceGetDatasUseIdPage(CustomGetDatasUseIdPage retryServiceGetDatasUseIdPage) {
        this.retryServiceGetDatasUseIdPage = retryServiceGetDatasUseIdPage;
    }

    public void setAfterService(AfterService afterService) {
        this.afterService = afterService;
    }

    public CustomGetDatas getRetryServiceCustomGetDatas() {
        return retryServiceCustomGetDatas;
    }

    public void setRetryServiceCustomGetDatas(CustomGetDatas retryServiceCustomGetDatas) {
        this.retryServiceCustomGetDatas = retryServiceCustomGetDatas;
    }

    public RetryService getRetryService() {
        return retryService;
    }

    public void setRetryService(RetryService retryService) {
        this.retryService = retryService;
    }

    public InitOption getInitOption() {
        return initOption;
    }

    public void setInitOption(InitOption initOption) {
        this.initOption = initOption;
    }
}
