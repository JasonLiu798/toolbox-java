package com.atjl.retry.api.exception;


public class RetryRegisteException extends RuntimeException {
    public RetryRegisteException() {
        super();
    }

    public RetryRegisteException(Throwable e) {
        super(e);
    }

    public RetryRegisteException(String msg, Throwable e) {
        super(msg, e);
    }

    public RetryRegisteException(String msg) {
        super("重试服务注册失败，" + msg);
    }
}
