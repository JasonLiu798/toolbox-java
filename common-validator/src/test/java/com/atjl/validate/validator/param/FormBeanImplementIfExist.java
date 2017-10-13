package com.atjl.validate.validator.param;

import com.alibaba.druid.pool.ManagedDataSource;
import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.noparam.Require;

import java.io.Closeable;

/**
 * 校验表单示例
 */
public class FormBeanImplementIfExist {

    StringField f1 = new StringField("字段1", new Require());
    StringField f2 = new StringField("字段1", new BeanExist(), new BeanImplementIf("f1", "dataSource", ManagedDataSource.class), new BeanImplementIf("f1", "dataSource", Closeable.class));
    //

//    StringField f3 = new StringField("字段3", new RequireIf("f1", "vvv", "f1=vvv时，不能为空"));
}
