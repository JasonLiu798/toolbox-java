package com.atjl.validate.validator.noparam;

import com.atjl.util.character.RegexUtil;
import com.atjl.validate.api.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.util.ValidateParseUtil;
import com.atjl.validate.validator.base.ValidatorBase;

/**
 * 时间戳校验器
 *
 * @author jasonliu
 */
public class Timestamp extends ValidatorBase {
    private static final String DFT_MSG = "时间戳格式错误";

    public Timestamp() {
        init(DFT_MSG);
    }

    public Timestamp(String msg) {
        init(msg);
    }

    private void init(String msg) {
        this.msg = msg;
    }

    public void validate(ValidateForm form, ValidateField field) {
        String raw = field.getRawValue();
        if (!RegexUtil.isDigit(raw)) {
            throw new ValidateException(this.msg);
        }
        Long l = Long.parseLong(raw);
        if (l < 0) {
            throw new ValidateException(this.msg);
        }
    }

    @Override
    public Validator parse(String str) {
        return ValidateParseUtil.simpleParse(Timestamp.class, str);
    }
}
