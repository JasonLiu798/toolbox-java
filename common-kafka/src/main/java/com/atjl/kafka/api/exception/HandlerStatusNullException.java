package com.atjl.kafka.api.exception;

/**
 * kafka common exception
 * @since 1.0
 */
public class HandlerStatusNullException extends MQException {

    private final static long serialVersionUID = 1L;

    public HandlerStatusNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public HandlerStatusNullException(String message) {
        super(message);
    }

    public HandlerStatusNullException(Throwable cause) {
        super(cause);
    }

    public HandlerStatusNullException() {
        super();
    }

}
