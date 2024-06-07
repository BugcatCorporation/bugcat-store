package com.bugcatcorp.app_bugcat_store.validacion;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class EmailFormatConstraintValidator implements ConstraintValidator<EmailFormatConstraint, String> {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    @Override
    public void initialize(EmailFormatConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return true;
        }
        return pattern.matcher(email).matches();
    }
}
