package com.atjl.retry.form;

import com.atjl.retry.api.option.GetDataType;
import com.atjl.retry.api.option.RetryAfterType;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.param.AnyOf;
import com.atjl.validate.validator.param.NumRange;
import com.atjl.validate.validator.param.RequireIf;
import com.atjl.validate.validator.noparam.AlphaNumDash;
import com.atjl.validate.validator.noparam.IsBoolean;
import com.atjl.validate.validator.noparam.Optional;
import com.atjl.validate.validator.noparam.Require;

public class InitOptionForm {

    StringField serviceName = new StringField("服务名", new Require(), new AlphaNumDash());

    StringField getDataType = new StringField("获取数据类型", new Require(), new AnyOf(CollectionUtil.newSet(
            GetDataType.CUSTOM_USEID.toString(),
            GetDataType.CUSTOM_USEPAGE.toString(),
            GetDataType.DEFAULT.toString())));

    StringField afterType = new StringField("后置服务类型", new Require(), new AnyOf(CollectionUtil.newSet(
            RetryAfterType.ALLSEQ.toString(),
            RetryAfterType.ALLREV.toString(),
            RetryAfterType.CUSTOM.toString(),
            RetryAfterType.DEFAULT.toString(),
            RetryAfterType.NONE.toString())));

    StringField intervalCoefficientOption = new StringField("重试间隔相关参数", new Require());

    /**
     * 超过此大小数据将使用分页查询
     */
    StringField pageSize = new StringField("分页页大小", new Require(), new NumRange(1L, 100L));

    /**
     * 最大20次
     */
    StringField retryMaxCount = new StringField("重试最大次数", new Require(), new NumRange(1L, 20L));


    StringField exceptionInstanceRetry = new StringField("是否立即重试", new Optional(), new IsBoolean());

    StringField instanceRetryOption = new StringField("立即重试相关参数", new RequireIf("exceptionInstanceRetry", "true", "开启立即重试后相关参数必填"));

    StringField retryTabMeta = new StringField("重试表元信息", new RequireIf("getDataType", "DEFAULT"));


    /**
     * 不超过两天
     *
     StringField intervalCoefficient = new StringField("重试间隔倍数", new Require(), new NumRange(1L, 580L));*/
    //StringField fixRate = new StringField("重试间隔倍数",new Require(),new NumRange(1L,580L));
}
