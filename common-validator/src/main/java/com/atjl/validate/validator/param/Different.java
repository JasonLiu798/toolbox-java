package com.atjl.validate.validator.param;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateComponentException;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.validator.base.RefFieldBase;

/**
 * 与其他字段 不相等 校验器
 *
 * @author jasonliu
 */
public class Different extends RefFieldBase {
    public static final String DFT_MSG = "必须与%s不相等";

    public Different(String refFieldKey) {
        init(refFieldKey, DFT_MSG, true);
    }

    /**
     * @param refField
     * @param msg
     */
    public Different(String refField, String msg) {
        init(refField, msg, false);
    }

    public void validate(ValidateForm form, ValidateField field) {
        String raw = field.getStrValue();
        ValidateField vref = form.getField(refField);
        if (vref == null) {
            throw new ValidateComponentException("参考域不存在");
        }
        String msg = this.msg;
        if (this.fmt) {
            msg = String.format(this.msg, vref.getLabel());
        }
        if (StringCheckUtil.equal(raw, vref.getStrValue())) {
            throw new ValidateException(msg);
        }
    }

    @Override
    public Validator parse(String str) {
        return null;
    }
}
