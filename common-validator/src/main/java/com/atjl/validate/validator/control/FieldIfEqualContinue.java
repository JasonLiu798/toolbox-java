package com.atjl.validate.validator.control;

import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateNotRecMsgException;
import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.validator.base.FieldIfEqualBase;

/**
 * 流程控制校验器
 * 指定字段为指定值时，继续后续校验，否则终止
 */
public class FieldIfEqualContinue extends FieldIfEqualBase {

    public FieldIfEqualContinue(String refField, String refVal) {
        init(refField, refVal, null, false);
    }

    @Override
    public void validate(ValidateForm form, ValidateField field) {
        if (!needChk(form)) {
            throw new ValidateNotRecMsgException();
        }
    }

    @Override
    public Validator parse(String str) {
        return null;
    }
}
