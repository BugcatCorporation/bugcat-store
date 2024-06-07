package com.bugcatcorp.app_bugcat_store.validacion;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailFormatConstraintValidator.class)
public @interface EmailFormatConstraint {
    String message() default "El formato del correo electrónico no es válido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
