package com.atjl.validate.validator.param.form;

import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.noparam.Email;
import com.atjl.validate.validator.noparam.Optional;
import com.atjl.validate.validator.param.RequireIf;

/**
 * 校验表单示例
 */
public class FormReqIf {
    StringField f1 = new StringField("字段1", new Optional(), new Email());
    StringField f2 = new StringField("字段2", new Optional());
    StringField f3 = new StringField("字段3", new RequireIf("f1", "vvv", "f1=vvv时，不能为空"));
}
