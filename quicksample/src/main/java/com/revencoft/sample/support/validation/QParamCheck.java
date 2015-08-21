/**
 * 
 */
package com.revencoft.sample.support.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author mengqingyan
 * @version 
 */

@Target(value={ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = QueryParamCheckValidator.class)
@Documented

public @interface QParamCheck {

	String message() default "{com.revencoft.constraints.qparamcheck}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
