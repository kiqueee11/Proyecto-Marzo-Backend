package com.example.auth_service.exceptions;

import java.net.http.HttpResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public class UserAuthException extends RuntimeException {

    private String errorCode;
    private int statusCode;
    private HttpStatus httpStatus;
    private String message;
    

    public UserAuthException(String errorCode, String message, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.statusCode = httpStatus.value();
        this.httpStatus = httpStatus;
        this.message = message;

        

    }

}
