package com.example.auth_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.auth_service.exceptions.UserAuthException;
import com.example.auth_service.response.AuthenticationResponse;

@ControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(UserAuthException.class)
    ResponseEntity<AuthenticationResponse<String>> handleException(UserAuthException e) {

        AuthenticationResponse<String> authenticationResponse = new AuthenticationResponse<>(false, e.getMessage(),
                null, e.getErrorCode(), e.getStatusCode(), e.getHttpStatus().getReasonPhrase());

        return ResponseEntity.status(authenticationResponse.getStatusCode()).body(authenticationResponse);

    }

}
