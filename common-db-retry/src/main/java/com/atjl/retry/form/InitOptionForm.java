package com.atjl.retry.form;

import com.atjl.retry.api.option.GetDataType;
import com.atjl.retry.api.option.RetryAfterType;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.validate.api.StringField;
import com.atjl.validate.validator.multiparam.AnyOf;
import com.atjl.validate.validator.multiparam.NumRange;
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

    /**
     * 不超过两天
     *
     StringField intervalCoefficient = new StringField("重试间隔倍数", new Require(), new NumRange(1L, 580L));*/

    /**
     * 超过此大小数据将使用分页查询
     */
    StringField pageSize = new StringField("分页页大小", new Require(), new NumRange(1L, 100L));

    /**
     * 最大20次
     */
    StringField retryMaxCount = new StringField("重试最大次数", new Require(), new NumRange(1L, 20L));


    StringField exceptionInstanceRetry = new StringField("出现异常是否立即重试", new Optional(), new IsBoolean());

    //    StringField needRetryMark = new StringField("", )

    //StringField fixRate = new StringField("重试间隔倍数",new Require(),new NumRange(1L,580L));

}
