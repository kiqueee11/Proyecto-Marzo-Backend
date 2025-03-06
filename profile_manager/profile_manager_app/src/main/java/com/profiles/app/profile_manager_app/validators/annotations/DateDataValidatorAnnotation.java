package com.profiles.app.profile_manager_app.validators.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.profiles.app.profile_manager_app.validators.DateDataValidator;

@Target({ java.lang.annotation.ElementType.PARAMETER })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateDataValidator.class)

public @interface DateDataValidatorAnnotation {
    String message() default "Invalid Date data";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
