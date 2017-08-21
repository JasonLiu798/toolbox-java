package com.atjl.cas.api;


import java.io.Serializable;

/**
 * 业务基类
 */
public class CASBaseException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;


    public CASBaseException() {
        super();
    }

    public CASBaseException(String message) {
        super(message);
    }

    public CASBaseException(String message, Throwable cause) {
        super(message, cause);
    }


    public CASBaseException(Throwable cause) {
        super(cause);
    }

    public CASBaseException(Throwable cause, String msg) {
        super(msg, cause);
    }

}
