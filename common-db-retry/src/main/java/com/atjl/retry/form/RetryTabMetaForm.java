package com.atjl.retry.form;


import com.atjl.validate.api.StringField;
import com.atjl.validate.validator.noparam.AlphaNumDash;
import com.atjl.validate.validator.noparam.Require;

public class RetryTabMetaForm {
    /**
     *     private String tab;//结果表
     private String idCol;//主键 列名
     private String retryCountCol;//尝试次数列名
     private String resCol;//结果 列名
     private String failReasonCol;//失败原因列名
     */
    StringField tab = new StringField("结果表A",new Require(),new AlphaNumDash());
    StringField idCol = new StringField("主键",new Require(),new AlphaNumDash());
    StringField retryCountCol = new StringField("尝试次数列名",new Require(),new AlphaNumDash());
    StringField resCol = new StringField("结果",new Require(),new AlphaNumDash());
    StringField failReasonCol = new StringField("失败原因列名",new Require(),new AlphaNumDash());
}
