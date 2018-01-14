package com.atjl.validate.form.ref;

import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.noparam.Email;
import com.atjl.validate.validator.noparam.Optional;
import com.atjl.validate.validator.param.Length;

/**
 * 校验表单示例
 */
public class FormRefEg2 {
    StringField f10 = new StringField("f10", new Optional(), new Email());
    StringField f11 = new StringField("f11", new Optional(), new Length(3));
}
