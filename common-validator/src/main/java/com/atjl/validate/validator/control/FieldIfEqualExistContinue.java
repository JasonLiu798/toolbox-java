package com.atjl.validate.validator.control;

import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateNotRecMsgException;
import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.validator.base.FieldIfEqualExistBase;

import java.util.Set;

/**
 * 流程控制校验器
 * 指定字段为指定值时，继续后续校验，否则终止
 */
public class FieldIfEqualExistContinue extends FieldIfEqualExistBase {

    public FieldIfEqualExistContinue(String refField, Set<String> refVal) {
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
