package com.atjl.kafka.api.exception;

/**
 * kafka common exception
 *
 * @since 1.0
 */
public class TopicRegistedException extends MQException {

    private final static long serialVersionUID = 1L;

    public TopicRegistedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TopicRegistedException(String message) {
        super(message);
    }

    public TopicRegistedException(Throwable cause) {
        super(cause);
    }

    public TopicRegistedException() {
        super();
    }

}
