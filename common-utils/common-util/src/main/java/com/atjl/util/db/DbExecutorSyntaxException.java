package com.atjl.util.db;

/**
 * 语法异常
 */
public class DbExecutorSyntaxException extends RuntimeException {
    public DbExecutorSyntaxException() {
        super();
    }

    public DbExecutorSyntaxException(Throwable e) {
        super(e);
    }

    public DbExecutorSyntaxException(String msg, Throwable e) {
        super(msg, e);
    }

    public DbExecutorSyntaxException(String msg) {
        super(msg);
    }
}
