package com.atjl.validate.validator.param.form;

import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.param.BeanExist;

/**
 * 校验表单示例
 */
public class FormBeanExist {
    StringField f1 = new StringField("字段1", new BeanExist());

//    StringField f3 = new StringField("字段3", new RequireIf("f1", "vvv", "f1=vvv时，不能为空"));
}
