package com.atjl.kafka.api.exception;

/**
 * kafka common exception
 * @since 1.0
 */
public class GetZookeeperException extends MQException {

    private final static long serialVersionUID = 1L;

    public GetZookeeperException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetZookeeperException(String message) {
        super(message);
    }

    public GetZookeeperException(Throwable cause) {
        super(cause);
    }

    public GetZookeeperException() {
        super();
    }

}
