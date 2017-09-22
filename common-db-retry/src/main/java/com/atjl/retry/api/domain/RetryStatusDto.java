package com.atjl.retry.api.domain;


import com.atjl.retry.api.option.InitOption;

public class RetryStatusDto {
    private String serviceName;
    private InitOption initOption;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public InitOption getInitOption() {
        return initOption;
    }

    public void setInitOption(InitOption initOption) {
        this.initOption = initOption;
    }
}
