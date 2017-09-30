package com.atjl.validate.validator.param;

import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.validator.base.EnumBase;

import java.util.Set;

/**
 * 枚举校验器
 *
 * @author jasonliu
 */
public class AnyOf extends EnumBase {
    public static final String DFT_MSG = "值只能为 %s";

    public AnyOf(Set<String> refs) {
        init(refs, DFT_MSG, true);
    }

    public AnyOf(Set<String> refs, String msg) {
        init(refs, msg, false);
    }

    public void validate(ValidateForm form, ValidateField field) {
        String raw = field.getStrValue();
        if (!refs.contains(raw)) {
            throw new ValidateException(this.msg);
        }
    }

    @Override
    public Validator parse(String str) {

        return null;
    }
}
