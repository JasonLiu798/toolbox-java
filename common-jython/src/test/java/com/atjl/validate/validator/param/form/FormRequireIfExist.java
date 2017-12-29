package com.atjl.validate.validator.param.form;

import com.atjl.util.collection.CollectionUtil;
import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.noparam.Email;
import com.atjl.validate.validator.noparam.Optional;
import com.atjl.validate.validator.param.RequireIfExist;

/**
 * 校验表单示例
 */
public class FormRequireIfExist {
    StringField f1 = new StringField("字段1", new Optional(), new Email());
    StringField f2 = new StringField("字段2", new Optional());
    StringField f3 = new StringField("字段3", new RequireIfExist("f1", CollectionUtil.newSet("vvv","bbb"), "f1=vvv或bbb时，不能为空"));
}
