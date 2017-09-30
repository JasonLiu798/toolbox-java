package com.atjl.validate.multithread;

import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.noparam.Require;

/**
 * 校验表单示例
 */
public class MultiThreadFormEg {
    StringField f1 = new StringField("字段email", new Require());
//    StringField f2 = new StringField("字段2", new Optional(), new Length(3));
}
