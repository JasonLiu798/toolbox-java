package com.atjl.validate.validator.base;


import com.atjl.util.character.StringCheckUtil;
import com.atjl.validate.api.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.api.exception.ValidateNotRecMsgException;
import com.atjl.validate.validator.base.EnumBase;

import java.util.Set;

/**
 * 指定的 *任意* 字段不为空时，此字段必填
 * 如果为空，则会中断后续校验器
 */
public abstract class RequireWithBase extends EnumBase {

    public RequireWithBase(Set<String> refs) {
        init(refs, DFT_MSG, true);
    }

    public RequireWithBase(Set<String> refs, String msg) {
        init(refs, msg, false);
    }

    public static final String TP_NOT_NULL = "NN";
    public static final String TP_NULL = "N";

    public boolean existNotNull(ValidateForm form) {
        boolean existNotNull = false;
        for (String refField : refs) {
            ValidateField f = form.getField(refField);
            if (!StringCheckUtil.isEmpty(f.getRawValue())) {
                existNotNull = true;
                break;
            }
        }
        return existNotNull;
    }

    public boolean existNull(ValidateForm form) {
        boolean existNull = false;
        for (String refField : refs) {
            ValidateField f = form.getField(refField);
            if (StringCheckUtil.isEmpty(f.getRawValue())) {
                existNull = true;
                break;
            }
        }
        return existNull;
    }


    public void validateBase(ValidateForm form, ValidateField field, String chkType, boolean error) {
        String raw = field.getRawValue();

        boolean exist;
        if (StringCheckUtil.equal(chkType, TP_NOT_NULL)) {
            exist = existNotNull(form);
        } else {
            exist = existNull(form);
        }

        if (exist == error) {
            if (StringCheckUtil.isEmpty(raw)) {
                throw new ValidateException(this.msg);
            }
        }

        if (StringCheckUtil.isEmpty(raw)) {
            throw new ValidateNotRecMsgException();
        }
    }
}
