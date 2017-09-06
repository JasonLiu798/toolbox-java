package com.atjl.validate.validator.noparam;

import com.atjl.util.character.RegexUtil;
import com.atjl.validate.api.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.util.ValidateParseUtil;
import com.atjl.validate.validator.base.ValidatorBase;

/**
 * IP 校验器
 *
 * @author jasonliu
 */
public class IPV4 extends ValidatorBase {
    public static final String DFT_MSG = "IP格式错误";

    public IPV4() {
        super(DFT_MSG);
    }

    public IPV4(String msg) {
        super(msg);
    }

    public void validate(ValidateForm form, ValidateField field) {
        String raw = field.getRawValue();
        if (!RegexUtil.isIPV4(raw)) {
            throw new ValidateException(this.msg);
        }
    }

    @Override
    public Validator parse(String str) {
        return ValidateParseUtil.simpleParse(IPV4.class, str);
    }
}
