package com.atjl.validate.validator.noparam;

import com.atjl.util.character.RegexUtil;
import com.atjl.validate.api.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.util.ValidateParseUtil;
import com.atjl.validate.validator.base.ValidatorBase;

/**
 * 字母
 *
 * @author jasonliu
 */
public class Alpha extends ValidatorBase {

    public static final String DFT_MSG = "非字母";

    public Alpha() {
        super(DFT_MSG);
    }

    public Alpha(String msg) {
        super(msg);
    }

    public void validate(ValidateForm form, ValidateField field) {
        String raw = field.getRawValue();
        if (!RegexUtil.isAlpha(raw)) {
            throw new ValidateException(this.msg);
        }
    }

    public Validator parse(String raw) {
        return ValidateParseUtil.simpleParse(Alpha.class, raw);
    }
}
