package com.atjl.dbtiming.domain.form;


import com.atjl.dbtiming.domain.constant.TimingConstant;
import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.noparam.Require;
import com.atjl.validate.validator.param.Exist;

public class NewTaskForm extends TaskCommonCheckForm {

    StringField tkey = new StringField("唯一编号", new Require(), new Exist(TimingConstant.TASK_TABLE, TimingConstant.TASK_TABLE_COL_KEY));

}
