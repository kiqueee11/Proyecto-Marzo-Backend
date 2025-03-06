package com.profiles.app.profile_manager_app.Exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException {
    private String errorCode;
    private int ststusCode;
    private String message;
    private HttpStatus httpStatus;

    public UserException(String errorCode, String message, HttpStatus httpStatus) {

        super(message);
        this.errorCode = errorCode;
        this.ststusCode = httpStatus.value();
        this.message = message;
        this.httpStatus = httpStatus;

    }

}