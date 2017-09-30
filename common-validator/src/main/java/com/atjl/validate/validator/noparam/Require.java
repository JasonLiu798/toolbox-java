package com.atjl.validate.validator.noparam;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.util.ValidateParseUtil;
import com.atjl.validate.validator.base.ValidatorBase;

public class Require extends ValidatorBase {

    public static final String DFT_MSG = "必填";

    public Require() {
        super(DFT_MSG);
    }

    public Require(String msg) {
        super(msg);
    }

    public void validate(ValidateForm form, ValidateField field) {
        if (StringCheckUtil.isEmpty(field.getStrValue())) {
            throw new ValidateException(this.msg);
        }
    }

    @Override
    public Validator parse(String str) {
        return ValidateParseUtil.simpleParse(Require.class, str);
    }
}
