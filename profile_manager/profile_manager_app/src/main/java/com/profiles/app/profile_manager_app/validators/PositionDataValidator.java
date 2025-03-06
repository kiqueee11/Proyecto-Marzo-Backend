package com.profiles.app.profile_manager_app.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.profiles.app.profile_manager_app.validators.annotations.PositionDataValidatorAnnotation;

public class PositionDataValidator
        implements ConstraintValidator<PositionDataValidatorAnnotation, String> {

    @Override
    public boolean isValid(String data, ConstraintValidatorContext context) {
        return coordenadasValidas(data);
    }

    private boolean coordenadasValidas(String posicion) {
        try {
            String[] parts = posicion.split(",");
            if (parts.length != 2) {
                return false;
            }

            double latitude = Double.parseDouble(parts[0].trim());
            double longitude = Double.parseDouble(parts[1].trim());

            return (latitude >= -90 && latitude <= 90) && (longitude >= -180 && longitude <= 180);
        } catch (Exception e) {
            return false;
        }
    }

}
