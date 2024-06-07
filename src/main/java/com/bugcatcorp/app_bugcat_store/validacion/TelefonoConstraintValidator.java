package com.bugcatcorp.app_bugcat_store.validacion;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TelefonoConstraintValidator implements ConstraintValidator<TelefonoConstraint, String> {
    @Override
    public void initialize(TelefonoConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String telefono, ConstraintValidatorContext context) {
        if (telefono == null) {
            return true;
        }
        // Verificar si el número de teléfono tiene exactamente 9 dígitos
        return telefono.matches("^\\d{9}$");
    }
}
