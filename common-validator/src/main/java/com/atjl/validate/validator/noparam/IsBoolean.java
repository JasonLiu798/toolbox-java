package com.atjl.validate.validator.noparam;

import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.util.ValidateCheckUtil;
import com.atjl.validate.validator.base.ValidatorBase;

/**
 * boolean校验器
 * 接收值：
 * Y,N,y,n,yes,no,true,false,T,F,t,f
 *
 * @author jasonliu
 */
public class IsBoolean extends ValidatorBase {
    public static final String DFT_MSG = "非布尔值";

    public IsBoolean() {
        super(DFT_MSG);
    }

    public IsBoolean(String msg) {
        super(msg);
    }

    public void validate(ValidateForm form, ValidateField field) {
        if (!ValidateCheckUtil.isBool(field.getStrValue())) {
            throw new ValidateException(this.msg);
        }
    }

    @Override
    public Validator parse(String str) {
        return null;
    }


}
