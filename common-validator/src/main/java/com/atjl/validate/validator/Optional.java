package com.atjl.validate.validator;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.validate.api.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.exception.ValidateContinueException;

/**
 * 可以为空
 */
public class Optional extends BaseValidator {

    public static final String DFT_MSG = "可选字段";

    public Optional() {
        super(DFT_MSG);
    }

    public void validate(ValidateForm form, ValidateField field) {
        String raw = field.getValue();
        if (StringCheckUtil.isEmpty(raw)) {
            throw new ValidateContinueException();
        }
    }
}
