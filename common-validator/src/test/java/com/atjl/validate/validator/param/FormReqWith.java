package com.atjl.validate.validator.param;

import com.atjl.util.collection.CollectionUtil;
import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.noparam.Email;
import com.atjl.validate.validator.noparam.Optional;

/**
 * 校验表单示例
 */
public class FormReqWith {
    StringField f1 = new StringField("字段1", new Optional(), new Email());
    StringField f2 = new StringField("字段2", new Optional());
    StringField f3 = new StringField("字段3", new RequireWith(CollectionUtil.newSet("f1", "f2"), "A和B不为空时必填"));
}
