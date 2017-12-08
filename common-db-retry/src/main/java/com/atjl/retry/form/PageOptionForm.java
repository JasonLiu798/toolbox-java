package com.atjl.retry.form;

import com.atjl.retry.api.option.GetDataType;
import com.atjl.retry.api.option.PageOption;
import com.atjl.retry.api.option.RetryAfterType;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.validate.api.annotation.ValidateObj;
import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.noparam.AlphaNumDash;
import com.atjl.validate.validator.noparam.Require;
import com.atjl.validate.validator.param.AnyOf;
import com.atjl.validate.validator.param.NumRange;

@ValidateObj(obj = PageOption.class)
public class PageOptionForm {

    StringField serviceName = new StringField("服务名", new Require(), new AlphaNumDash());

    StringField getDataType = new StringField("获取数据类型", new Require(), new AnyOf(CollectionUtil.newSet(
            GetDataType.CUSTOM_USEID.toString(),
            GetDataType.CUSTOM_USEPAGE.toString(),
            GetDataType.CUSTOM_BATCH_USEPAGE.toString(),
            GetDataType.DEFAULT.toString())));

    StringField afterType = new StringField("后置服务类型", new Require(), new AnyOf(CollectionUtil.newSet(
            RetryAfterType.ALLSEQ.toString(),
            RetryAfterType.ALLREV.toString(),
            RetryAfterType.CUSTOM.toString(),
            RetryAfterType.DEFAULT.toString(),
            RetryAfterType.NONE.toString())));

    /**
     * 超过此大小数据将使用分页查询
     */
    StringField pageSize = new StringField("分页页大小", new Require(), new NumRange(1L, 1000L));

}
