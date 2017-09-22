package com.atjl.common.api.annotation;

import java.lang.annotation.*;

/**
 * 非线程安全
 *
 * @since 1.0
 */
@Documented
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.TYPE, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.CLASS)
public @interface ThreadUnSafe {
}
