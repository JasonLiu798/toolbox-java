package com.atjl.validate.validator.oneparam;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.validate.api.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.ValidateInitException;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.validator.base.ValidatorBase;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 指定日期格式
 *
 * @author jasonliu
 */
public class DateFormat extends ValidatorBase {
    private static final String DFT_MSG = "不符合日期格式%s";

    private String refFmt;//参考的日期格式

    public DateFormat(String refDateFmt) {
        init(refDateFmt, DFT_MSG, true);
    }

    public DateFormat(String refDateFmt, String msg) {
        init(refDateFmt, msg, false);
    }

    private void init(String refDateFmt, String msg, boolean fmt) {
        this.refFmt = refDateFmt;
        if (fmt) {
            this.msg = String.format(msg, refFmt);
        } else {
            this.msg = msg;
        }
        chk();
    }

    private void chk() {
        if (StringCheckUtil.isEmpty(refFmt)) {
            throw new ValidateInitException("日期格式校验器，参考格式为空");
        }
    }

    public void validate(ValidateForm form, ValidateField field) {
        SimpleDateFormat sdf = new SimpleDateFormat(this.refFmt);
        String raw = field.getRawValue();
        try {
            sdf.parse(raw);
        } catch (ParseException e) {
            throw new ValidateException(this.msg);
        }
    }

    @Override
    public Validator parse(String str) {
        return null;
    }
}
