package com.atjl.validate.validator.param.form;

import com.atjl.validate.api.field.ListField;
import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.noparam.Optional;
import com.atjl.validate.validator.param.ListLength;

/**
 * 校验表单示例
 */
public class FormListLengthInner {
	
    StringField f1 = new StringField("inner",new Optional());
    
    
//    StringField f2 = new StringField("字段2", new Optional());
//    StringField f3 = new StringField("字段3", new RequireIf("f1", "vvv", "f1=vvv时，不能为空"));
}
