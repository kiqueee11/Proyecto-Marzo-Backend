package com.example.auth_service.validator.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import com.example.auth_service.validator.classes.EmailValidatorClass;

import jakarta.validation.Constraint;

@Target({ElementType.PARAMETER})
@Constraint(validatedBy  = EmailValidatorClass.class)
@Retention(RetentionPolicy.RUNTIME) 
public @interface EmailValidatorAnnotation {

}
