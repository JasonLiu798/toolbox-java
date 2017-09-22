package com.atjl.kafka.api.exception;

/**
 * kafka common exception
 * @since 1.0
 */
public class ConfigFormatErrorException extends MQException {

    private final static long serialVersionUID = 1L;

    public ConfigFormatErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigFormatErrorException(String message) {
        super(message);
    }

    public ConfigFormatErrorException(Throwable cause) {
        super(cause);
    }

    public ConfigFormatErrorException() {
        super();
    }

}
