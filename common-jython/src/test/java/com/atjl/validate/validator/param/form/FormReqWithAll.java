package com.atjl.validate.validator.param.form;

import com.atjl.util.collection.CollectionUtil;
import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.noparam.Email;
import com.atjl.validate.validator.noparam.Optional;
import com.atjl.validate.validator.param.RequireWithAll;

/**
 * 校验表单示例
 */
public class FormReqWithAll {
    StringField f1 = new StringField("字段1", new Optional(), new Email());
    StringField f2 = new StringField("字段2", new Optional());
    StringField f3 = new StringField("字段3", new RequireWithAll(CollectionUtil.newSet("f1", "f2"), "A和B都不为空时必填"));
}
