package com.atjl.validate.validator.param;


import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.validator.base.RequireWithBase;

import java.util.Set;

/**
 * 指定的 *任意* 字段不为空时，此字段必填
 * 如果为空，则会中断后续校验器
 */
public class RequireWith extends RequireWithBase {
    public static final String DFT_MSG = "%s存在不为空字段，该字段必填";

    public RequireWith(Set<String> refs) {
        super(refs,DFT_MSG);
    }

    public RequireWith(Set<String> refs, String msg) {
        super(refs, msg);
    }

    public void validate(ValidateForm form, ValidateField field) {
        validateBase(form, field, RequireWithBase.TP_NOT_NULL, true);
    }

    @Override
    public Validator parse(String str) {
        return null;
    }
}
