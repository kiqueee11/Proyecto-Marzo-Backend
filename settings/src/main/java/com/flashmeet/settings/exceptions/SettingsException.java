package com.flashmeet.settings.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class SettingsException extends RuntimeException {
    private String errorCode;
    private int ststusCode;
    private String message;
    private HttpStatus httpStatus;

    public SettingsException(String errorCode, String message, HttpStatus httpStatus) {

        super(message);
        this.errorCode = errorCode;
        this.ststusCode = httpStatus.value();
        this.message = message;
        this.httpStatus = httpStatus;

    }

}