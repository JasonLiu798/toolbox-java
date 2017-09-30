package com.atjl.dbtiming.api.exception;

import com.atjl.dbtiming.api.RetCode;

import java.io.Serializable;

/**
 * timing exception
 *
 * @author JasonLiu
 */
public class TimingException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    public RetCode errorCode;

    public TimingException() {
    }

    public TimingException(RetCode errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public TimingException(String message) {
        super(message);
    }

    public TimingException(String message, Throwable cause, RetCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public TimingException(String message, RetCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public TimingException(Throwable cause, RetCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

}
