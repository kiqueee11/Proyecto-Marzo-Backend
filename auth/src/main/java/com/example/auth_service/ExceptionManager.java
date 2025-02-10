package com.example.auth_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_service.exceptions.UserAuthException;
@RestController

@ControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(UserAuthException.class)
    ResponseEntity<ResponseEntity<Object>>handleException(){

        return ResponseEntity.badRequest().build();


        
    }
    
    
}
