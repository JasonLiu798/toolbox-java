package com.atjl.validate.api.exception;

/**
 * 校验组件异常
 *
 * @author jasonliu
 */
public class ValidateComponentException extends RuntimeException {
    public ValidateComponentException() {
        super();
    }

    public ValidateComponentException(Throwable e) {
        super(e);
    }

    public ValidateComponentException(String msg, Throwable e) {
        super(msg, e);
    }

    public ValidateComponentException(String msg) {
        super(msg);
    }
}
