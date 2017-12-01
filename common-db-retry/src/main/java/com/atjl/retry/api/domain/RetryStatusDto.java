package com.atjl.retry.api.domain;


import com.atjl.retry.api.option.PageOption;
import com.atjl.retry.api.option.RetryInstanceOption;
import com.atjl.retry.api.option.RetryOption;

public class RetryStatusDto {
    private String serviceName;
    private RetryOption retryOption;
    private RetryInstanceOption retryInstanceOption;
    private PageOption pageOption;

    public RetryInstanceOption getRetryInstanceOption() {
        return retryInstanceOption;
    }

    public void setRetryInstanceOption(RetryInstanceOption retryInstanceOption) {
        this.retryInstanceOption = retryInstanceOption;
    }

    public PageOption getPageOption() {
        return pageOption;
    }

    public void setPageOption(PageOption pageOption) {
        this.pageOption = pageOption;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public RetryOption getRetryOption() {
        return retryOption;
    }

    public void setRetryOption(RetryOption retryOption) {
        this.retryOption = retryOption;
    }
}
