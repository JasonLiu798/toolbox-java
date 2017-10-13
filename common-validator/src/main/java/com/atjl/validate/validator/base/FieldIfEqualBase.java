package com.atjl.validate.validator.base;


import com.atjl.util.character.StringCheckUtil;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.exception.ValidateInitException;
import com.atjl.validate.api.field.ValidateField;

/**
 * 指定字段为指定值时，才做校验
 */
public abstract class FieldIfEqualBase extends ValidatorBase {

    protected String refField;
    protected String refVal;

//    public FieldIfEqualBase(String refField, String refVal, String msg) {
//        init(refField, refVal, msg, false);
//    }


    protected void init(String refField, String refVal, String msg, boolean fmt) {
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


    protected boolean needChk(ValidateForm form) {
        ValidateField refF = form.getField(refField);
        /**
         * todo：初始化表单时，校验
         */
        if (refF == null) {
            throw new ValidateInitException("参考域不存在");
        }
        if (StringCheckUtil.equal(refF.getStrValue(), this.refVal)) {
            return true;
        }
        return false;
    }
}
