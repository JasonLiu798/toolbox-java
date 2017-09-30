package com.atjl.retry.api.option;


public class InitOptionFactory {
    public static InitOption buildDefault() {
        InitOption initOption = new InitOption();
        return initOption;
    }

    public static InitOption buildSimple() {
        InitOption initOption = new InitOption();
//        initOption.setGetDataType();
        return initOption;
    }
}
