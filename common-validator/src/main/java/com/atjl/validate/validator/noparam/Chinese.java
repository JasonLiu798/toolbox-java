package com.atjl.validate.validator.noparam;

import com.atjl.util.character.RegexUtil;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.util.ValidateParseUtil;
import com.atjl.validate.validator.base.ValidatorBase;

/**
 * 中文
 *
 * @author jasonliu
 */
public class Chinese extends ValidatorBase {

    public static final String DFT_MSG = "非字母";

    public Chinese() {
        super(DFT_MSG);
    }

    public Chinese(String msg) {
        super(msg);
    }

    public void validate(ValidateForm form, ValidateField field) {
        if (!RegexUtil.isChinese(field.getStrValue())) {
            throw new ValidateException(this.msg);
        }
    }

    public Validator parse(String raw) {
        return ValidateParseUtil.simpleParse(Chinese.class, raw);
    }
}
