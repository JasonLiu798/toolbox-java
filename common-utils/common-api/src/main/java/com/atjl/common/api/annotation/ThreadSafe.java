package com.atjl.common.api.annotation;

import java.lang.annotation.*;

/**
 * ThreadSafe field,class
 *
 * @since 1.0
 */
@Documented
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface ThreadSafe {
}
