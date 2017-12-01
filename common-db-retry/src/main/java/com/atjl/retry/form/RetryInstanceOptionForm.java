package com.atjl.retry.form;

import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.noparam.IsBoolean;
import com.atjl.validate.validator.noparam.Optional;
import com.atjl.validate.validator.param.RequireIf;

public class RetryInstanceOptionForm extends PageOptionForm{

    StringField exceptionInstanceRetry = new StringField("是否立即重试", new Optional(), new IsBoolean());

    StringField instanceRetryOption = new StringField("立即重试相关参数", new RequireIf("exceptionInstanceRetry", "true", "开启立即重试后相关参数必填"));

}
