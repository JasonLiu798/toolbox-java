package com.atjl.validate.validator.base;


import com.atjl.validate.api.Validator;

/**
 * 校验器基类
 *
 * @author jasonliu
 */
public abstract class ValidatorBase implements Validator {

    public static final String DFT_MSG = "校验失败";
    protected String msg;

    public ValidatorBase() {
        this.msg = DFT_MSG;
    }

    /**
     * 校验器相关参数校验
     * <p>
     * abstract public void chk();
     */
    public ValidatorBase(String msg) {
        this.msg = msg;
    }


}
