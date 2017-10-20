package com.atjl.office.api.exception;


public class ExcelException extends RuntimeException {
    public ExcelException() {
        super();
    }

    public ExcelException(Throwable e) {
        super(e);
    }

    public ExcelException(String msg, Throwable e) {
        super(msg, e);
    }

    public ExcelException(String msg) {
        super(msg);
    }
}
