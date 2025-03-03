package com.example.auth_service.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

@AllArgsConstructor

public class AuthenticationResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private String errorCode;
    private int statusCode;
    private String statusDescripcion;

    public static <T> AuthenticationResponse<T> success(boolean success, String message, T data,
            HttpStatus httpStatus) {

        return new AuthenticationResponse<T>(success, message, data, message, httpStatus.value(),
                httpStatus.getReasonPhrase());

    }

    public static <T> AuthenticationResponse<T> failure(boolean success, String message, T data, String errorCode,
            HttpStatus httpStatus) {

        return new AuthenticationResponse<T>(success, message, data, errorCode, httpStatus.value(),
                httpStatus.getReasonPhrase());
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", errorCode='" + errorCode + '\'' +
                ", statusCode=" + statusCode +
                ", statusDescripcion='" + statusDescripcion + '\'' +
                '}';
    }

}
