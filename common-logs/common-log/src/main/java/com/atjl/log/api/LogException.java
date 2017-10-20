package com.atjl.log.api;


import java.io.Serializable;

public class LogException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    public LogException(){
        super("日志异常");
    }
    public LogException(String msg) {
        super(msg);
    }


}
