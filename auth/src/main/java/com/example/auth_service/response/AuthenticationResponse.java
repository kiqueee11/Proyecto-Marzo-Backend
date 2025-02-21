package com.example.auth_service.response;

import lombok.Data;

@Data
public class AuthenticationResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private String errorCode;
    private int statusCode;
    private String statusDescripcion;



    // Constructor para respuestas exitosas
    public AuthenticationResponse(boolean success, String message, T data, int statusCode, String statusDescripcion) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // Constructor para respuestas con error
    public AuthenticationResponse(boolean success, String message, T data, String errorCode, int statusCode, String statusDescripcion) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errorCode = errorCode;
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
