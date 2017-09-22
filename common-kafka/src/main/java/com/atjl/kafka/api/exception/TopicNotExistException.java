package com.atjl.kafka.api.exception;

/**
 * kafka common exception
 * @since 1.0
 */
public class TopicNotExistException extends MQException {

    private final static long serialVersionUID = 1L;

    public TopicNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public TopicNotExistException(String message) {
        super(message);
    }

    public TopicNotExistException(Throwable cause) {
        super(cause);
    }

    public TopicNotExistException() {
        super();
    }

}
