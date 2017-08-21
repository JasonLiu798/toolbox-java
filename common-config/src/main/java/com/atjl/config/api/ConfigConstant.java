package com.atjl.config.api;


import com.atjl.configdb.api.ConfigDbConstant;

/**
 * 共用常量
 */
public class ConfigConstant {

    private ConfigConstant() {
        throw new UnsupportedOperationException();
    }


    //配置服务，db ts_dictionary
    public static final String CONF_SERVICE_USE_DB_PLAIN = ConfigDbConstant.CONF_DB_PLAIN_SERVICE;
    //配置服务，db tm_config
    public static final String CONF_SERVICE_USE_DB_TREE = ConfigDbConstant.CONF_DB_TREE_SERVICE;


    //配置服务，通过 配置文件 properties 实现
    public static final String CONF_SERVICE_PROP = "configServiceProp";




}
