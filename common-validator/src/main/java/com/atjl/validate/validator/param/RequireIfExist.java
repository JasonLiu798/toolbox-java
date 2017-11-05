package com.atjl.validate.validator.param;


import com.atjl.util.character.StringCheckUtil;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.validator.base.FieldIfEqualExistBase;

import java.util.Set;

/**
 * 指定的 字段 值为 x 时，此字段必填
 * 如果为空，则会中断后续校验器
 */
public class RequireIfExist extends FieldIfEqualExistBase {
    private static final String DFT_MSG = "%s字段值为%s，该字段必填";

    public RequireIfExist(String refField, Set<String> refVal) {
        init(refField, refVal, DFT_MSG, true);
    }

    public RequireIfExist(String refField, Set<String> refVal, String msg) {
        init(refField, refVal, msg, false);
    }

    public void validate(ValidateForm form, ValidateField field) {
        if(needChk(form)){
            if (StringCheckUtil.isEmpty(field.getStrValue())) {
                throw new ValidateException(this.msg);
            }
        }
    }

    @Override
    public Validator parse(String str) {
        return null;
    }
}
