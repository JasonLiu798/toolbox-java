package com.atjl.office.api.exception;


public class WordException extends RuntimeException {
    public WordException() {
        super();
    }

    public WordException(Throwable e) {
        super(e);
    }

    public WordException(String msg, Throwable e) {
        super(msg, e);
    }

    public WordException(String msg) {
        super(msg);
    }
}
