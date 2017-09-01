package com.atjl.validate.validator;

import com.atjl.util.character.RegexUtil;
import com.atjl.validate.api.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.exception.ValidateException;

public class Email extends BaseValidator {
    public static final String DFT_MSG = "email格式错误";

    public Email() {
        super(DFT_MSG);
    }

    public Email(String msg) {
        super(msg);
    }

    public void validate(ValidateForm form, ValidateField field) {
        String raw = field.getValue();
        if (!RegexUtil.isEmail(raw)) {
            throw new ValidateException(this.msg);
        }
    }
}
