package com.atjl.retry.api.option;


public class OptionFactory {
    public static InitOption buildDefault() {
        InitOption initOption = new InitOption();
//        initOption.setInstanceRetryOption();
        return initOption;
    }
}
