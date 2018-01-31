package com.atjl.validate.validator.param.form;

import com.atjl.util.collection.CollectionUtil;
import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.noparam.Require;
import com.atjl.validate.validator.param.ExistWith;

/**
 * 校验表单示例
 */
public class FormExistWith {
    StringField tkey = new StringField("字段1", new ExistWith("ts_task_v1", "TKEY", CollectionUtil.newKVL("valid","VALID") ," and TSTATUS ='F' "));//" and VALID = 'Y' "));

    StringField valid = new StringField("字段2",new Require());

//    StringField f2 = new StringField("字段2", new Optional());
//    StringField f3 = new StringField("字段3", new RequireIf("f1", "vvv", "f1=vvv时，不能为空"));
}
