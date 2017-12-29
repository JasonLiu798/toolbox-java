package com.atjl.validate.form.ref;

import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.noparam.Email;
import com.atjl.validate.validator.noparam.Optional;
import com.atjl.validate.validator.noparam.Require;
import com.atjl.validate.validator.param.Length;

/**
 * 校验表单示例
 */
public class FormListEg1 {
    StringField f3 = new StringField("f3", new Require(), new Email());
    StringField f4 = new StringField("f4", new Optional(),new Length(3));
}
