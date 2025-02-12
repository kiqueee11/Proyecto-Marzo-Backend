package com.example.auth_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.auth_service.exceptions.UserAuthException;

@ControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(UserAuthException.class)
    ResponseEntity<Object>handleException(UserAuthException e){

        return ResponseEntity.badRequest().body(e.getMessage());


        
    }
    
    
}
