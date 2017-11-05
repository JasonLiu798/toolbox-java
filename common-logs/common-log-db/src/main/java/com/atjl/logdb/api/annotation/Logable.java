package com.atjl.logdb.api.annotation;

import java.lang.annotation.*;

/**
 * 需要记日志
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Logable {
}
