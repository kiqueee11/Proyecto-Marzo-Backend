package com.flashmeet.messages.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.flashmeet.messages.validator.MessageModelValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = MessageModelValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageDTOValidator {

    String message() default "Invalid MessageDTO"; 

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}
