package com.atjl.validate.api.annotation;

import java.lang.annotation.*;

/**
 *
 */
@Documented
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Verifiable {
	public Class form();
}
