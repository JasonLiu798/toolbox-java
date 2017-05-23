package com.jason798.exception;

public class ErrorCode {
    public final String code;
    public final String msg;
    public final String action;
    public final ErrorCode cause;

    public ErrorCode(String code, String message, String action) {
        this(code, message, action, null);
    }

    public ErrorCode(String code, String message, String action, ErrorCode cause) {
        this.code = code;
        this.msg = message;
        this.action = action;
        this.cause = cause;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getAction() {
        return action;
    }

    public ErrorCode getCause() {
        return cause;
    }

    @Override
    public String toString() {
        return code + ":" + msg + "," + action;
    }


}
