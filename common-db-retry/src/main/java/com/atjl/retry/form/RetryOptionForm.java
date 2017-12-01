package com.atjl.retry.form;

import com.atjl.retry.api.option.RetryOption;
import com.atjl.validate.api.annotation.ValidateObj;
import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.noparam.Require;
import com.atjl.validate.validator.param.NumRange;
import com.atjl.validate.validator.param.RequireIf;

@ValidateObj(obj = RetryOption.class)
public class RetryOptionForm extends RetryInstanceOptionForm {

//    StringField retrySwitch = new StringField("是否启用重试相关操作", new Require());

    StringField intervalCoefficientOption = new StringField("重试间隔相关参数", new Require());

    /**
     * 最大20次
     */
    StringField retryMaxCount = new StringField("重试最大次数", new Require(), new NumRange(1L, 20L));

    StringField retryTabMeta = new StringField("重试表元信息", new RequireIf("getDataType", "DEFAULT"));


    /**
     * 不超过两天
     *
     StringField intervalCoefficient = new StringField("重试间隔倍数", new Require(), new NumRange(1L, 580L));*/
    //StringField fixRate = new StringField("重试间隔倍数",new Require(),new NumRange(1L,580L));
}
