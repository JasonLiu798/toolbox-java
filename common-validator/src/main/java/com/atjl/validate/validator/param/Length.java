package com.atjl.validate.validator.param;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.exception.ValidateInitException;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.validator.base.ValidatorBase;

/**
 *
 */
public class Length extends ValidatorBase {

    private static final String DFT_MSG = "长度需要在 %s,%s 之间";

    private Integer min;//长度最小参考值
    private Integer max;//长度最大参考值

    public Length(Integer min, Integer max) {
        super(String.format(DFT_MSG, min, max));
        this.min = min;
        this.max = max;
        chk();
    }

    public Length(Integer max) {
        super(String.format(DFT_MSG, 0, max));
        this.min = 0;
        this.max = max;
        chk();
    }

    public void chk() {
        if (this.min == null || this.max == null) {
            throw new ValidateInitException("长度校验，参考值min或max为空");
        }
        if (this.max < this.min) {
            throw new ValidateInitException("长度校验，参考值min必须小于max");
        }
        if (this.min < 0) {
            throw new ValidateInitException("长度校验，参考值min必须大于零");
        }
    }

    public void validate(ValidateForm form, ValidateField field) {
        String raw = field.getStrValue();
        if (!StringCheckUtil.isEmpty(raw)) {
            if (raw.length() > this.max || raw.length() < this.min) {
                throw new ValidateException(this.msg);
            }
        } else {
            if (min > 0) {
                throw new ValidateException(this.msg);
            }
        }
    }

    @Override
    public Validator parse(String str) {
        return null;
    }
}
