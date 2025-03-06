package com.profiles.app.profile_manager_app.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserServiceResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private String errorCode;
    private int statusCode;
    private String statusDescripcion;

    public static <T> UserServiceResponse<T> success(String message, T data, HttpStatus httpStatus) {
        return new UserServiceResponse<T>(true, message, data, null, httpStatus.value(), httpStatus.getReasonPhrase());
    }

    public static <T> UserServiceResponse<T> failure(String message, T data, HttpStatus httpStatus, String errorCode) {
        return new UserServiceResponse<T>(false, message, data, errorCode, httpStatus.value(),
                httpStatus.getReasonPhrase());
    }

    @Override
    public String toString() {
        return "UserServiceResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", errorCode='" + errorCode + '\'' +
                ", statusCode=" + statusCode +
                ", statusDescripcion='" + statusDescripcion + '\'' +
                '}';
    }

}
