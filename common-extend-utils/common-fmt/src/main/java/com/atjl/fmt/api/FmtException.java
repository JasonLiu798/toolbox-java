package com.atjl.fmt.api;

import java.io.Serializable;

public class FmtException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    public FmtException() {
        super("模板异常");
    }

    public FmtException(String msg) {
        super(msg);
    }


}
