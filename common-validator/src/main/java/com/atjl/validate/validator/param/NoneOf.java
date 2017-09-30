package com.atjl.validate.validator.param;

import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.validator.base.EnumBase;

import java.util.Set;

/**
 * 枚举反向 校验器
 *
 * @author jasonliu
 */
public class NoneOf extends EnumBase {
    public static final String DFT_MSG = "值不能为 %s";

    public NoneOf(Set<String> refs) {
        init(refs, DFT_MSG, true);
    }

    public NoneOf(Set<String> refs, String msg) {
        init(refs, msg, false);
    }

    public void validate(ValidateForm form, ValidateField field) {
        String raw = field.getStrValue();
        if (refs.contains(raw)) {
            throw new ValidateException(this.msg);
        }
    }

    @Override
    public Validator parse(String str) {
        return null;
    }
}
