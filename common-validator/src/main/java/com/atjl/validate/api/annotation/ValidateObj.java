package com.atjl.validate.api.annotation;

import java.lang.annotation.*;

/**
 * 方便查看校验的对象类
 */
@Documented
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface ValidateObj {
    public Class[] form();
}
