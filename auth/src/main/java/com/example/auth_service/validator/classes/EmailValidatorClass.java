package com.example.auth_service.validator.classes;

import org.apache.commons.validator.routines.EmailValidator;

import com.example.auth_service.validator.annotations.EmailValidatorAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidatorClass implements ConstraintValidator<EmailValidatorAnnotation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return isEmailValid(value, context);
    }

    private boolean isEmailValid(String email, ConstraintValidatorContext context) {
        EmailValidator validator = EmailValidator.getInstance();

        boolean isValid = true;
        if (email == null || email.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Email cannot be empty").addConstraintViolation();
            isValid &= false;
        }

        if (!validator.isValid(email)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("The email format is wrong").addConstraintViolation();
            isValid &= false;
        }
        return isValid;
    }

}
