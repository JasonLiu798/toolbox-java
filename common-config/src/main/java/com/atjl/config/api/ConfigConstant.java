package com.atjl.config.api;


/**
 * 共用常量
 */
public class ConfigConstant {

    private ConfigConstant() {
        throw new UnsupportedOperationException();
    }


    //配置服务，通过 db 字典方式 实现
    public static final String CONF_SERVICE_USE_DICT = "configServiceUseDict";


    //配置服务，通过 配置文件 properties 实现
    public static final String CONF_SERVICE_PROP = "configServiceProp";




}
