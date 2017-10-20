package com.atjl.util.api;


public class ConfigPropException extends RuntimeException {
    public ConfigPropException() {
        super();
    }

    public ConfigPropException(Throwable e) {
        super(e);
    }

    public ConfigPropException(String msg, Throwable e) {
        super(msg,e);
    }

    public ConfigPropException(String msg) {
        super("配置异常，" + msg);
    }
}
