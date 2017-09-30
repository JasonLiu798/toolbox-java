package com.atjl.validate.form.ref;

import com.atjl.validate.api.field.FormField;
import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.noparam.Email;
import com.atjl.validate.validator.noparam.Optional;
import com.atjl.validate.validator.noparam.Require;

/**
 * 校验表单示例
 */
public class FormRefEg {
    StringField f1 = new StringField("f1", new Require(), new Email());
    FormField f2 = new FormField("f2", FormRefEg1.class, new Optional());
}
