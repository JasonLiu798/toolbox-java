package com.atjl.common.api.annotation;

import java.lang.annotation.*;

/**
 * For future version,can't use NOW!
 *
 * @since 1.0
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
public @interface ForFuture {
}
