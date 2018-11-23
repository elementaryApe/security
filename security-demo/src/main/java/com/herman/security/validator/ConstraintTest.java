package com.herman.security.validator;

import com.herman.security.validator.impl.ConstraintTestImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义校验注解
 * Created by hsh on 18/11/16.
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConstraintTestImpl.class)
public @interface ConstraintTest {

    String message() ;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
