package com.example.auth_service.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.auth_service.exceptions.UserAuthException;

@ControllerAdvice

public class UserAuthExceptionHandler {

    @ExceptionHandler(value = UserAuthException.class)
    public ResponseEntity<Object> handler(UserAuthException userAuthException) {
        return ResponseEntity.badRequest().body(userAuthException.getMessage());
    }

}
