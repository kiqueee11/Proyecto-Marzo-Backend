package com.profiles.app.profile_manager_app.validators.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.profiles.app.profile_manager_app.validators.PositionDataValidator;

@Documented
@Target({java.lang.annotation.ElementType.PARAMETER})
@Constraint(validatedBy = PositionDataValidator.class)
public @interface PositionDataValidatorAnnotation {
    String message() default "Invalid Psotion data";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
