package com.flashmeet.messages.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;

public class MessageServiceException extends RuntimeException {

    String errorCode;
    int ststusCode;
    String message;
    HttpStatus httpStatus;

    public MessageServiceException(String errorCode, String message, HttpStatus httpStatus) {

        super(message);
        this.errorCode = errorCode;
        this.ststusCode = httpStatus.value();
        this.message = message;
        this.httpStatus = httpStatus;

    }

}
