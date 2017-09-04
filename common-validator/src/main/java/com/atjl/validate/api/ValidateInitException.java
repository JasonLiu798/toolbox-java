package com.atjl.validate.api;

/**
 * 初始化异常
 */
public class ValidateInitException extends RuntimeException {
    public ValidateInitException() {
        super();
    }

    public ValidateInitException(Throwable e) {
        super(e);
    }

    public ValidateInitException(String msg, Throwable e) {
        super(msg, e);
    }

    public ValidateInitException(String msg) {
        super(msg);
    }
}
