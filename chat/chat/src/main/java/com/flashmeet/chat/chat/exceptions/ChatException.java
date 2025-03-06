package com.flashmeet.chat.chat.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;
@Data
public class ChatException extends RuntimeException {

    String errorCode;
    int statusCode;
    String message;
    HttpStatus httpStatus;

    public ChatException(String errorCode, String message, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.statusCode = httpStatus.value();
        this.message = message;
        this.httpStatus = httpStatus;

    }

}
