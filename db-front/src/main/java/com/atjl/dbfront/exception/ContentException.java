package com.atjl.dbfront.exception;

/**
 *
 * @author jasonliu
 */
public class ContentException extends RuntimeException {
    public ContentException() {
        super();
    }

    public ContentException(Throwable e) {
        super(e);
    }

    public ContentException(String msg, Throwable e) {
        super(msg, e);
    }

    public ContentException(String msg) {
        super(msg);
    }
}
