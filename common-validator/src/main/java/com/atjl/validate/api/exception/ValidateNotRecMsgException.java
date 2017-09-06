package com.atjl.validate.api.exception;

/**
 *
 *
 * @author jasonliu
 */
public class ValidateNotRecMsgException extends RuntimeException {
    public ValidateNotRecMsgException() {
        super();
    }

    public ValidateNotRecMsgException(Throwable e) {
        super(e);
    }

    public ValidateNotRecMsgException(String msg, Throwable e) {
        super(msg, e);
    }

    public ValidateNotRecMsgException(String msg) {
        super(msg);
    }
}
