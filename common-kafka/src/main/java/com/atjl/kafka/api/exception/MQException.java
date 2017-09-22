package com.atjl.kafka.api.exception;


public class MQException extends RuntimeException {
    public MQException() {
        super();
    }

    public MQException(Throwable e) {
        super(e);
    }

    public MQException(String msg, Throwable e) {
        super(msg, e);
    }

    public MQException(String msg) {
        super(msg);
    }
}