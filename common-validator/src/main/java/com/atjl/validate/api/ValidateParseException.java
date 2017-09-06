package com.atjl.validate.api;

/**
 * 字符串校验器 转 class校验器对象 失败
 */
public class ValidateParseException extends RuntimeException {
    public ValidateParseException() {
        super();
    }

    public ValidateParseException(Throwable e) {
        super(e);
    }

    public ValidateParseException(String msg, Throwable e) {
        super(msg, e);
    }

    public ValidateParseException(String msg) {
        super(msg);
    }
}
