package com.profiles.app.profile_manager_app.ExceptionHandlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.profiles.app.profile_manager_app.Exceptions.UserException;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value=UserException.class)
    public ResponseEntity<Object> handleUserException(UserException e) {

        return ResponseEntity.badRequest().body(e.getMessage());
    }
    
}
