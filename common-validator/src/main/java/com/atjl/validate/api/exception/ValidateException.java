package com.atjl.validate.api.exception;

/**
 * 校验异常
 *
 * @author jasonliu
 */
public class ValidateException extends RuntimeException {
    public ValidateException() {
        super();
    }

    public ValidateException(Throwable e) {
        super(e);
    }

    public ValidateException(String msg, Throwable e) {
        super(msg, e);
    }

    public ValidateException(String msg) {
        super(msg);
    }
}
