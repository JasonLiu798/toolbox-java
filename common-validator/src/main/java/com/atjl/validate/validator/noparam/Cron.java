package com.atjl.validate.validator.noparam;

import com.atjl.util.cron.CronExpression;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.util.ValidateParseUtil;
import com.atjl.validate.validator.base.ValidatorBase;

/**
 * cron 校验器
 *
 * @author jasonliu
 */
public class Cron extends ValidatorBase {
    public static final String DFT_MSG = "cron表达式不合法";

    public Cron() {
        super(DFT_MSG);
    }

    public Cron(String msg) {
        super(msg);
    }

    public void validate(ValidateForm form, ValidateField field) {
        try {
            new CronExpression(field.getStrValue());
        } catch (Exception e) {
            throw new ValidateException(this.msg);
        }
    }

    @Override
    public Validator parse(String str) {
        return ValidateParseUtil.simpleParse(Cron.class, str);
    }
}
