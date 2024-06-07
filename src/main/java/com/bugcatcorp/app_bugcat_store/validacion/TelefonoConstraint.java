package com.bugcatcorp.app_bugcat_store.validacion;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TelefonoConstraintValidator.class)
public @interface TelefonoConstraint {
    String message() default "El número de teléfono debe tener exactamente 9 dígitos";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
