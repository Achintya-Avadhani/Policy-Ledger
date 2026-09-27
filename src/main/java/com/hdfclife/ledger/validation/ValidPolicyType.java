package com.hdfclife.ledger.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = PolicyTypeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPolicyType {

    String message() default "Product type must be one of: TERM, ULIP, ENDOWMENT";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
