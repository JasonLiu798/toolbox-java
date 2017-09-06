package com.atjl.validate.validator.oneparam;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.validate.api.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateComponentException;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.validator.base.RefFieldBase;

/**
 * 与其他字段 相等 校验器
 *
 * @author jasonliu
 */
public class Same extends RefFieldBase {
    public static final String DFT_MSG = "必须与%s相等";

    public Same(String refFieldKey) {
        init(refFieldKey, DFT_MSG, true);
        this.fmt = true;
    }

    /**
     * @param refField
     * @param msg
     */
    public Same(String refField, String msg) {
        init(refField, msg, false);
    }

    public void validate(ValidateForm form, ValidateField field) {
        String raw = field.getRawValue();
        ValidateField vref = form.getField(refField);
        if (vref == null) {
            throw new ValidateComponentException("参考域不存在");
        }
        String msg = this.msg;
        if (this.fmt) {
            msg = String.format(this.msg, vref.getLabel());
        }
        if (!StringCheckUtil.equal(raw, vref.getRawValue())) {
            throw new ValidateException(msg);
        }
    }

    @Override
    public Validator parse(String str) {
        return null;
    }
}
