package com.atjl.retry.api.exception;


public class RetryExecuteException extends RuntimeException {
    public RetryExecuteException() {
        super();
    }

    public RetryExecuteException(Throwable e) {
        super(e);
    }

    public RetryExecuteException(String msg, Throwable e) {
        super(msg, e);
    }

    public RetryExecuteException(String msg) {
        super("重试服务执行失败，" + msg);
    }
}
