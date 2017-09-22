package com.atjl.kafka.api.exception;

/**
 * @since 1.0
 */
public class RegStatusNullException extends MQException {

    private final static long serialVersionUID = 1L;

    public RegStatusNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegStatusNullException(String message) {
        super(message);
    }

    public RegStatusNullException(Throwable cause) {
        super(cause);
    }

    public RegStatusNullException() {
        super();
    }

}
