package com.atjl.validate.validator;


import com.atjl.validate.api.Validator;

public abstract class BaseValidator implements Validator {

    public static final String DFT_MSG = "校验失败";
    protected String msg;

    public BaseValidator() {
        this.msg = DFT_MSG;
    }

    public BaseValidator(String msg) {
        this.msg = msg;
    }


}
