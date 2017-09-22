package com.atjl.validate.validator.multiparam;

import com.atjl.validate.api.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.validator.base.RequireWithBase;

import java.util.Set;

/**
 * 指定的 *所有* 字段都为空时，此字段必填
 */
public class RequireWithOutAll extends RequireWithBase {
    public static final String DFT_MSG = "%s均为空，该字段必填";

    public RequireWithOutAll(Set<String> refs) {
        super(refs);
    }

    public RequireWithOutAll(Set<String> refs, String msg) {
        super(refs, msg);
    }

    public void validate(ValidateForm form, ValidateField field) {
        //不存在非空，且值为空 抛异常
        validateBase(form, field, RequireWithBase.TP_NOT_NULL, false);
    }

    @Override
    public Validator parse(String str) {
        return null;
    }
}
