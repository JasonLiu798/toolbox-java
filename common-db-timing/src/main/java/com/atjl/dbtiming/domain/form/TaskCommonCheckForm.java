package com.atjl.dbtiming.domain.form;

import com.atjl.validate.api.field.FormField;
import com.atjl.validate.validator.noparam.Require;

public class TaskCommonCheckForm {
    FormField taskConf = new FormField("任务参数", TaskConfForm.class, new Require());
}
