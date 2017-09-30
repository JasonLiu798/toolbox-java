package com.atjl.validate.validator.param;


import com.atjl.util.character.StringCheckUtil;
import com.atjl.validate.api.*;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.api.exception.ValidateInitException;
import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.validator.base.ValidatorBase;

/**
 * 指定的 字段 值为 x 时，此字段必填
 * 如果为空，则会中断后续校验器
 */
public class RequireIf extends ValidatorBase {
    private static final String DFT_MSG = "%s字段值为%s，该字段必填";
    private String refField;
    private String refVal;

    public RequireIf(String refField, String refVal) {
        init(refField, refVal, DFT_MSG, true);
    }

    public RequireIf(String refField, String refVal, String msg) {
        init(refField, refVal, msg, false);
    }

    private void init(String refField, String refVal, String msg, boolean fmt) {
        this.refField = refField;
        this.refVal = refVal;
        chk();
        if (fmt) {
            this.msg = String.format(msg, refField, refVal);
        } else {
            this.msg = msg;
        }
    }

    private void chk() {
        if (StringCheckUtil.isEmpty(refField) || StringCheckUtil.isEmpty(refVal)) {
            throw new ValidateInitException("参考域字段名或字段值为空");
        }
    }


    public void validate(ValidateForm form, ValidateField field) {
        ValidateField refF = form.getField(refField);
        /**
         * todo：初始化表单时，校验
         */
        if (refF == null) {
            throw new ValidateInitException("参考域不存在");
        }
        if (StringCheckUtil.equal(refF.getStrValue(), this.refVal)) {
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
