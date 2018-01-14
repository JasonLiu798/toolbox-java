package com.atjl.validate.form.ref;

import com.atjl.validate.api.field.ListField;
import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.noparam.Email;
import com.atjl.validate.validator.noparam.Optional;
import com.atjl.validate.validator.noparam.Require;

/**
 * 校验表单示例
 */
public class FormListEg {
    StringField f1 = new StringField("f1", new Require(), new Email());
    ListField f2 = new ListField("f2", FormListEg1.class, new Optional());
}
