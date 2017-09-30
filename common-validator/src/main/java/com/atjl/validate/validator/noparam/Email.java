package com.atjl.validate.validator.noparam;

import com.atjl.util.character.RegexUtil;
import com.atjl.validate.api.*;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.util.ValidateParseUtil;
import com.atjl.validate.validator.base.ValidatorBase;

/**
 * email校验器
 *
 * @author jasonliu
 */
public class Email extends ValidatorBase {

    public static final String DFT_MSG = "email格式错误";

    public Email() {
        super(DFT_MSG);
    }

    public Email(String msg) {
        super(msg);
    }

    public void validate(ValidateForm form, ValidateField field) {
        if (!RegexUtil.isEmail(field.getStrValue())) {
            throw new ValidateException(this.msg);
        }
    }

    public Validator parse(String raw) {
        return ValidateParseUtil.simpleParse(Email.class, raw);
    }
}
