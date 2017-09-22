package com.atjl.kafka.api.exception;


public class FailedFetchException extends RuntimeException {
    public FailedFetchException() {
        super();
    }

    public FailedFetchException(Throwable e) {
        super(e);
    }

    public FailedFetchException(String msg, Throwable e) {
        super(msg, e);
    }

    public FailedFetchException(String msg) {
        super(msg);
    }
}