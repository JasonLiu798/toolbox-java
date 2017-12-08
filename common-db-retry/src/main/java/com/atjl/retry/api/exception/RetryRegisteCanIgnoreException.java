package com.atjl.retry.api.exception;

/**
 * 可忽略的注册异常
 */
public class RetryRegisteCanIgnoreException extends RuntimeException {
    public RetryRegisteCanIgnoreException() {
        super();
    }

    public RetryRegisteCanIgnoreException(Throwable e) {
        super(e);
    }

    public RetryRegisteCanIgnoreException(String msg, Throwable e) {
        super(msg, e);
    }

    public RetryRegisteCanIgnoreException(String msg) {
        super("重试服务注册失败，" + msg);
    }
}
