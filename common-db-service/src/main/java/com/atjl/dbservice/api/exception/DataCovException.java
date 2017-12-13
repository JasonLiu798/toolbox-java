package com.atjl.dbservice.api.exception;


public class DataCovException extends RuntimeException {
    public DataCovException() {
        super();
    }

    public DataCovException(Throwable e) {
        super(e);
    }

    public DataCovException(String msg, Throwable e) {
        super(msg, e);
    }

    public DataCovException(String msg) {
        super("转换异常，" + msg);
    }
}
