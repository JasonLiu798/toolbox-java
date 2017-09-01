package com.atjl.validate.validator;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.validate.api.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.exception.ValidateException;

public class Length extends BaseValidator {

    private static final String DFT_MSG = "最大长度为";

    private Long compare;

    public Length(Long compare) {
        super(DFT_MSG + compare);
    }

    public Length(Long compare, String msg) {
        super(msg);
        this.compare = compare;
    }

    public void validate(ValidateForm form, ValidateField field) {
        String raw = field.getValue();
        if (!StringCheckUtil.isEmpty(raw)) {
            if (raw.length() > compare) {
                throw new ValidateException(this.msg);
            }
        }
    }
}
