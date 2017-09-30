package com.atjl.validate.validator.param;

import com.atjl.util.character.RegexUtil;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.exception.ValidateInitException;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.validator.base.ValidatorBase;

/**
 * 整型（长整型）范围校验，min，max包含在内
 * PS：内部校验是否为数字
 *
 * @author jasonliu
 */
public class NumRange extends ValidatorBase {

    private static final String DFT_MSG = "数字大小需要在 %s,%s 之间";

    private Long min;//长度最小参考值
    private Long max;//长度最大参考值

    public NumRange(Long min, Long max, String msg) {
        init(min, max, msg, false);
    }

    public NumRange(Long min, Long max) {
        init(min, max, DFT_MSG, true);
    }

    public NumRange(Long max) {
        init(0L, max, DFT_MSG, true);
    }

    private void init(Long min, Long max, String msg, boolean fmt) {
        if (fmt) {
            this.msg = String.format(DFT_MSG, min, max);
        } else {
            this.msg = msg;
        }
        this.min = min;
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
        if (StringCheckUtil.isEmpty(raw)) {
            throw new ValidateException(this.msg);
        }
        if (!RegexUtil.isInteger(raw)) {
            throw new ValidateException(this.msg);
        }
        Long rawL = Long.parseLong(raw);
        if (rawL < this.min || rawL > this.max) {
            throw new ValidateException(this.msg);
        }
    }

    @Override
    public Validator parse(String str) {
        return null;
    }
}
