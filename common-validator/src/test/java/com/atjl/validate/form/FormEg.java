package com.atjl.validate.form;

import com.atjl.validate.api.StringField;
import com.atjl.validate.validator.noparam.Email;
import com.atjl.validate.validator.oneparam.Length;
import com.atjl.validate.validator.noparam.Optional;
import com.atjl.validate.validator.noparam.Require;

/**
 * 校验表单示例
 */
public class FormEg {
    static StringField f1 = new StringField("字段email", new Require(), new Email());
    static StringField f2 = new StringField("字段2", new Optional(), new Length(3));
}
