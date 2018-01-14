package com.atjl.validate.form.ref;

import com.atjl.validate.api.field.FormField;
import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.noparam.Email;
import com.atjl.validate.validator.noparam.Optional;
import com.atjl.validate.validator.noparam.Require;

/**
 * 校验表单示例
 */
public class FormRefEg1 {
    StringField f3 = new StringField("f3", new Require(), new Email());
    FormField f4 = new FormField("f4", FormRefEg2.class, new Optional());
}
