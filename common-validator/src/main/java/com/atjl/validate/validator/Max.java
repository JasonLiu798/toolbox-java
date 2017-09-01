package com.atjl.validate.validator;

import com.atjl.util.character.RegexUtil;
import com.atjl.validate.api.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.exception.ValidateException;

public class Max extends BaseValidator {

    public static final String DFT_MSG = "最大值只能为";

    private Long compare;

    public Max(Long compare) {
        super(DFT_MSG + compare);
    }

    public Max(Long compare, String msg) {
        super(msg);
        this.compare = compare;
    }


    public void validate(ValidateForm form, ValidateField field) {
        String raw = field.getValue();
        if (!RegexUtil.isInteger(raw)) {
            throw new ValidateException(this.msg);
        }
        Long lval = Long.parseLong(raw);
        if (lval > compare) {
            throw new ValidateException(this.msg);
        }
    }
}
