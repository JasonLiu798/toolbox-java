package com.atjl.dbtiming.domain.form;


import com.atjl.dbtiming.api.ICond;
import com.atjl.dbtiming.api.ITaskParam;
import com.atjl.dbtiming.api.domain.TaskType;
import com.atjl.dbtiming.domain.constant.TimingCheckConstant;
import com.atjl.dbtiming.domain.constant.TimingParamConstant;
import com.atjl.util.character.RegexUtil;
import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.control.FieldIfEqualContinue;
import com.atjl.validate.validator.noparam.Cron;
import com.atjl.validate.validator.noparam.IsBoolean;
import com.atjl.validate.validator.noparam.Optional;
import com.atjl.validate.validator.noparam.Require;
import com.atjl.validate.validator.param.BeanExist;
import com.atjl.validate.validator.param.BeanImplementIf;
import com.atjl.validate.validator.param.NumRange;
import com.atjl.validate.validator.param.RequireIf;

public class TaskConfForm {

    StringField taskType = new StringField("任务类型", new Require());

    StringField service = new StringField("业务类", new Require(), new BeanExist(), new BeanImplementIf("hasParam", RegexUtil.TRUE, ITaskParam.class), new BeanImplementIf("hasCond", RegexUtil.TRUE, ICond.class));

    StringField timeUnit = new StringField("时间单位", new RequireIf(TimingCheckConstant.TASK_TYPE, TaskType.INTERVAL.toString()));

    StringField cronExpression = new StringField("cron表达式", new FieldIfEqualContinue(TimingCheckConstant.TASK_TYPE, TaskType.CRON.toString()), new Cron());

    StringField delay = new StringField("延迟时间", new FieldIfEqualContinue(TimingCheckConstant.TASK_TYPE, TaskType.INTERVAL.toString()), new NumRange(0L, Long.MAX_VALUE));

    StringField interval = new StringField("间隔时间", new FieldIfEqualContinue(TimingCheckConstant.TASK_TYPE, TaskType.INTERVAL.toString()), new NumRange(1L, Long.MAX_VALUE));

    /**
     * ############# 执行前可选项 ######################
     */
    StringField hasParam = new StringField("是否有参数", new Require(), new IsBoolean());

    StringField param = new StringField("参数", new RequireIf(TimingParamConstant.HAS_PARAM, RegexUtil.TRUE));

    /**
     * ################# 提前终止选项 ################
     */
    StringField hasCond = new StringField("是否停止条件", new Require(), new IsBoolean());

    StringField maxRunCnt = new StringField("最大执行次数", new Require(), new NumRange(0L, Long.MAX_VALUE));

    StringField hasRunCnt = new StringField("已经执行次数", new Optional(), new NumRange(0L, Long.MAX_VALUE));

}
