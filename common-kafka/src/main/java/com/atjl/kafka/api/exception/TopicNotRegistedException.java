package com.atjl.kafka.api.exception;

/**
 * kafka common exception
 * @since 1.0
 */
public class TopicNotRegistedException extends MQException {

    private final static long serialVersionUID = 1L;

    public TopicNotRegistedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TopicNotRegistedException(String message) {
        super(message);
    }

    public TopicNotRegistedException(Throwable cause) {
        super(cause);
    }

    public TopicNotRegistedException() {
        super();
    }

}
