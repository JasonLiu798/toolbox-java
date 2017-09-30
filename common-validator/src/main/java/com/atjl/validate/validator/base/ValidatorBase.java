package com.atjl.validate.validator.base;


import com.atjl.validate.api.Validator;

import java.io.Serializable;

/**
 * 校验器基类
 *
 * @author jasonliu
 */
public abstract class ValidatorBase implements Validator, Serializable {

    private static final long serialVersionUID = 34910104552539864L;

    private static final String DFT_MSG = "校验失败";
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValidatorBase)) return false;
        ValidatorBase that = (ValidatorBase) o;
        return msg != null ? msg.equals(that.msg) : that.msg == null;
    }

    @Override
    public int hashCode() {
        return msg != null ? msg.hashCode() : 0;
    }
}
