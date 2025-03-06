package com.example.auth_service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.auth_service.exceptions.UserAuthException;
import com.example.auth_service.response.AuthenticationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

@ControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(UserAuthException.class)
    ResponseEntity<AuthenticationResponse<String>> handleException(UserAuthException e) {

        AuthenticationResponse<String> authenticationResponse = new AuthenticationResponse<>(false, e.getMessage(),
                null, e.getErrorCode(), e.getStatusCode(), e.getHttpStatus().getReasonPhrase());

        return ResponseEntity.status(authenticationResponse.getStatusCode()).body(authenticationResponse);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AuthenticationResponse<String>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonErrors = errrosToJson(methodArgumentNotValidException);
        System.out.println(jsonErrors);

        String message = objectMapper.readTree(jsonErrors).toString();
        System.out.println(message);

        AuthenticationResponse<String> messageServiceResponse = AuthenticationResponse.failure(false, message, null,
                jsonErrors, HttpStatus.BAD_REQUEST);

        return ResponseEntity.badRequest().body(messageServiceResponse);

    }

    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<AuthenticationResponse<String>> handleBadCredentialsExceptio(BadCredentialsException e) {

        AuthenticationResponse<String> authenticationResponse = AuthenticationResponse.failure(false, e.getMessage(),
                null, "BAD_CREDENTIALS", HttpStatus.UNAUTHORIZED);

        return ResponseEntity.status(authenticationResponse.getStatusCode()).body(authenticationResponse);

    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<AuthenticationResponse<String>> handleExceptions(Exception e) {

        AuthenticationResponse<String> authenticationResponse = AuthenticationResponse.failure(false, e.getMessage(),
                null, "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.status(authenticationResponse.getStatusCode()).body(authenticationResponse);

    }

    private String errrosToJson(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<FieldError> errors = methodArgumentNotValidException.getFieldErrors();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (int i = 0; i < errors.size(); i++) {
            FieldError error = errors.get(i);
            stringBuilder.append("\"" + error.getField() + "\":\"" + error.getDefaultMessage() + "\"");
            if (i < errors.size() - 1)
                stringBuilder.append(",");

        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }

}
