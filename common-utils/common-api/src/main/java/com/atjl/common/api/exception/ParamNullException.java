package com.atjl.common.api.exception;


public class ParamNullException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ParamNullException(){
        super("参数为空");
    }
    public ParamNullException(String msg) {
        super(msg);
    }


}
