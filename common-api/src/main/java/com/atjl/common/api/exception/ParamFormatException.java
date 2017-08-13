package com.atjl.common.api.exception;


public class ParamFormatException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ParamFormatException(){
        super("参数格式错误");
    }

    public ParamFormatException(Object param) {
        super("参数"+param+"格式错误");
    }


}
