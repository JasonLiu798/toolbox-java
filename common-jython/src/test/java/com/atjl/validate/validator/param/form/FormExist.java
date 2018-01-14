package com.atjl.validate.validator.param.form;

import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.param.Exist;

/**
 * 校验表单示例
 */
public class FormExist {
    StringField tkey = new StringField("字段1", new Exist("ts_task", "TKEY", " and VALID = 'Y' "));
//    StringField f2 = new StringField("字段2", new Optional());
//    StringField f3 = new StringField("字段3", new RequireIf("f1", "vvv", "f1=vvv时，不能为空"));
}
