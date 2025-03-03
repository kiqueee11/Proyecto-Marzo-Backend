package com.profiles.app.profile_manager_app.validators;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.profiles.app.profile_manager_app.validators.annotations.DateDataValidatorAnnotation;

public class DateDataValidator implements ConstraintValidator<DateDataValidatorAnnotation, String> {

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {

        return esValida(arg0);
    }

    public static boolean esValida(String dateStr) {
        try {
            LocalDateTime.parse(dateStr, DateTimeFormatter.ISO_DATE_TIME);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
