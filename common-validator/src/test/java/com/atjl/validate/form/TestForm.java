package com.atjl.validate.form;


import com.atjl.validate.api.StringField;
import com.atjl.validate.validator.Email;
import com.atjl.validate.validator.Length;
import com.atjl.validate.validator.Require;


public class TestForm extends BaseForm {
    StringField f1 = new StringField("字段email", new Require(), new Email());
    StringField f2 = new StringField("字段2", new Require(), new Length(3L));

}
