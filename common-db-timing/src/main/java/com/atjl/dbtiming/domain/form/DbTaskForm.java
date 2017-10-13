package com.atjl.dbtiming.domain.form;

import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.noparam.Require;

public class DbTaskForm extends TaskCommonCheckForm {

    StringField tid = new StringField("主键", new Require());
    StringField tkey = new StringField("唯一编号", new Require());

}
