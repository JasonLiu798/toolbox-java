package com.atjl.validate.exception;


public class ValidateContinueException extends RuntimeException {
    public ValidateContinueException() {
        super();
    }

    public ValidateContinueException(Throwable e) {
        super(e);
    }

    public ValidateContinueException(String msg, Throwable e) {
        super(msg, e);
    }

    public ValidateContinueException(String msg) {
        super(msg);
    }
}
