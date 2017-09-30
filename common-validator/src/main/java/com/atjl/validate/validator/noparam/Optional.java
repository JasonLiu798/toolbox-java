package com.atjl.validate.validator.noparam;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateNotRecMsgException;
import com.atjl.validate.util.ValidateParseUtil;
import com.atjl.validate.validator.base.ValidatorBase;

/**
 * 可以为空，如果为空，则后续校验器 不再校验，不为空则继续
 *
 * @author jasonliu
 */
public class Optional extends ValidatorBase {

    public static final String DFT_MSG = "可选字段";

    public Optional() {
        super(DFT_MSG);
    }

    public Optional(String msg) {
        this.msg = msg;
    }

    public void validate(ValidateForm form, ValidateField field) {
        if (StringCheckUtil.isEmpty(field.getStrValue())) {
            throw new ValidateNotRecMsgException();
        }
    }

    @Override
    public Validator parse(String str) {
        return ValidateParseUtil.simpleParse(Optional.class, str);
    }
}
