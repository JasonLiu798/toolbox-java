package com.atjl.jfeild.api.exception;


import java.io.Serializable;

public class JsonFieldException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public JsonFieldException() {
        super();
    }

    public JsonFieldException(String message) {
        super(message);
    }

    public JsonFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonFieldException(Throwable cause) {
        super(cause);
    }

    public JsonFieldException(Throwable cause, String msg) {
        super(msg, cause);
    }

}

