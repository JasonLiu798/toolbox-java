package com.atjl.validate.validator.param;

import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.validator.base.RequireWithBase;

import java.util.Set;

/**
 * 指定的 *所有* 字段不为空时，此字段必填
 */
public class RequireWithAll extends RequireWithBase {
    public static final String DFT_MSG = "%s都不为空，该字段必填";

    public RequireWithAll(Set<String> refs) {
        super(refs, DFT_MSG);
    }

    public RequireWithAll(Set<String> refs, String msg) {
        super(refs, msg);
    }

    public void validate(ValidateForm form, ValidateField field) {
        validateBase(form, field, RequireWithBase.TP_NULL, false);
    }

    @Override
    public Validator parse(String str) {
        return null;
    }
}
