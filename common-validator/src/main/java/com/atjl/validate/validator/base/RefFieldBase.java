package com.atjl.validate.validator.base;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.exception.ValidateInitException;

/**
 * 需要引用另一个field的校验器
 *
 * @author jasonliu
 */
public abstract class RefFieldBase extends ValidatorBase {

    protected String refField;
    protected boolean fmt;

    public void init(String refField, String msg, boolean fmt) {
        this.msg = msg;
        this.refField = refField;
        this.fmt = fmt;
        chk();
    }

    private void chk() {
        if (StringCheckUtil.isEmpty(this.refField)) {
            throw new ValidateInitException("参考字段key为空");
        }
    }

    public abstract void validate(ValidateForm form, ValidateField field);
}
