package com.atjl.validate.validator.oneparam;

import com.atjl.util.character.RegexUtil;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.validate.api.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.ValidateInitException;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.validator.base.ValidatorBase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 *
 */
public class Regex extends ValidatorBase {
    private static final String DFT_MSG = "不符合正则%s";

    private String regex;//正则原始值
    private Pattern pattern;//编译后对象

    public Regex(String regex) {
        init(regex, DFT_MSG, true);
    }

    public Regex(String regex, String msg) {
        init(regex, msg, false);
    }

    private void init(String regex, String msg, boolean fmt) {
        this.regex = regex;
        if (fmt) {
            this.msg = String.format(msg, regex);
        } else {
            this.msg = msg;
        }
        chk();
    }

    private void chk() {
        if (StringCheckUtil.isEmpty(regex)) {
            throw new ValidateInitException("正则校验器，正则表达式空");
        }
        this.pattern = RegexUtil.getPattern(this.regex);
    }

    public void validate(ValidateForm form, ValidateField field) {
        String raw = field.getRawValue();
        Matcher m = this.pattern.matcher(raw);
        if (!m.find()) {
            throw new ValidateException(this.msg);
        }
    }

    @Override
    public Validator parse(String str) {
        return null;
    }
}
