package com.atjl.validate.validator;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.validate.api.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.exception.ValidateException;

public class Require extends BaseValidator {

    public static final String DFT_MSG = "必填";

    public Require() {
        super(DFT_MSG);
    }

    public Require(String msg) {
        super(msg);
    }

    public void validate(ValidateForm form, ValidateField field) {
        String raw = field.getValue();
        if (StringCheckUtil.isEmpty(raw)) {
            throw new ValidateException(this.msg);
        }
    }
}
