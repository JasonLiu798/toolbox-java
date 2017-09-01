package com.atjl.util.api;


public class UtilException extends RuntimeException {
    public UtilException() {
        super();
    }

    public UtilException(Throwable e) {
        super(e);
    }

    public UtilException(String msg, Throwable e) {
        super(msg, e);
    }

    public UtilException(String msg) {
        super(msg);
    }
}
