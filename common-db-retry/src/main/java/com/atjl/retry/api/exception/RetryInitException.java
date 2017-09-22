package com.atjl.retry.api.exception;


public class RetryInitException extends RuntimeException {
    public RetryInitException() {
        super();
    }

    public RetryInitException(Throwable e) {
        super(e);
    }

    public RetryInitException(String msg, Throwable e) {
        super(msg, e);
    }

    public RetryInitException(String msg) {
        super("重试服务初始化失败，" + msg);
    }
}
