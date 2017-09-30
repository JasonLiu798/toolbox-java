package com.atjl.validate.validator.noparam;

import com.atjl.util.character.RegexUtil;
import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.util.ValidateParseUtil;
import com.atjl.validate.validator.base.ValidatorBase;

/**
 * uuid校验器
 *
 * @author jasonliu
 */
public class Uuid extends ValidatorBase {
    public static final String DFT_MSG = "uuid格式错误";

    public Uuid() {
        super(DFT_MSG);
    }

    public Uuid(String msg) {
        super(msg);
    }

    public void validate(ValidateForm form, ValidateField field) {
        if (!RegexUtil.isUUID(field.getStrValue())) {
            throw new ValidateException(this.msg);
        }
    }

    @Override
    public Validator parse(String str) {
        return ValidateParseUtil.simpleParse(Uuid.class, str);
    }
}
