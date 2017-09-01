package com.atjl.validate.exception;


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
