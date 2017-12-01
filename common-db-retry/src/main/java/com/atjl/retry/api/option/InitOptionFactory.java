package com.atjl.retry.api.option;


public class InitOptionFactory {
    public static RetryOption buildDefault() {
        RetryOption initOption = new RetryOption();
        return initOption;
    }

    public static RetryOption buildSimple() {
        RetryOption initOption = new RetryOption();
//        initOption.setGetDataType();
        return initOption;
    }
}
