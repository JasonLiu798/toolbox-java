package com.atjl.validate.validator.multiparam;


import com.atjl.validate.api.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.validator.base.ValidatorBase;

import java.util.Set;

/**
 * 指定的 字段 值为 x 时，此字段必填
 * 如果为空，则会中断后续校验器
 */
public class RequireIf extends ValidatorBase {
    public static final String DFT_MSG = "%s字段值为%s，该字段必填";
    private String refField;
    private String refVal;

    public RequireIf(String refField, String refVal) {

    }

    public RequireIf(String refField, String refVal, String msg) {

    }

    private void init(String refField, String refVal, String msg, boolean fmt) {
        this.refField = refField;
        this.refVal = refVal;
        if (fmt) {

        } else {
//
        }
    }

    private void chk() {

    }


    public RequireIf(Set<String> refs, String msg) {
//        super(refs, msg);
    }

    public void validate(ValidateForm form, ValidateField field) {
//        validateBase(form, field, RequireWithBase.TP_NOT_NULL, true);
    }

    @Override
    public Validator parse(String str) {
        return null;
    }
}
