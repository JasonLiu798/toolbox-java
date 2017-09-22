package com.atjl.kafka.api.exception;

/**
 * kafka common exception
 * @since 1.0
 */
public class HandlerStatusErrorException extends MQException {

    private final static long serialVersionUID = 1L;

    public HandlerStatusErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public HandlerStatusErrorException(String message) {
        super(message);
    }

    public HandlerStatusErrorException(Throwable cause) {
        super(cause);
    }

    public HandlerStatusErrorException() {
        super();
    }

}
