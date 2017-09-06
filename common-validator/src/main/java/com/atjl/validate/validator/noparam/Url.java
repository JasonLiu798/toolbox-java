package com.atjl.validate.validator.noparam;

import com.atjl.util.character.RegexUtil;
import com.atjl.validate.api.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.util.ValidateParseUtil;
import com.atjl.validate.validator.base.ValidatorBase;

/**
 * url校验器
 *
 * @author jasonliu
 */
public class Url extends ValidatorBase {
    public static final String DFT_MSG = "url格式错误";

    public Url() {
        super(DFT_MSG);
    }

    public Url(String msg) {
        super(msg);
    }

    public void validate(ValidateForm form, ValidateField field) {
        String raw = field.getRawValue();
        if (!RegexUtil.isURL(raw)) {
            throw new ValidateException(this.msg);
        }
    }

    @Override
    public Validator parse(String str) {
        return ValidateParseUtil.simpleParse(Url.class, str);
    }
}
