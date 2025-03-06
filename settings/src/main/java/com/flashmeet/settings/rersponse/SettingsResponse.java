package com.flashmeet.settings.rersponse;

import org.springframework.http.HttpStatus;

import lombok.*;

@Data
@AllArgsConstructor
public class SettingsResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private String errorCode;
    private int statusCode;
    private String statusDescripcion;

    public static <T> SettingsResponse<T> success(String message, T data, HttpStatus httpStatus) {
        return new SettingsResponse<T>(true, message, data, null, httpStatus.value(), httpStatus.getReasonPhrase());
    }

    public static <T> SettingsResponse<T> failure(String message, T data, HttpStatus httpStatus, String errorCode) {
        return new SettingsResponse<T>(false, message, data, errorCode, httpStatus.value(),
                httpStatus.getReasonPhrase());
    }

    @Override
    public String toString() {
        return "SettingsResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", errorCode='" + errorCode + '\'' +
                ", statusCode=" + statusCode +
                ", statusDescripcion='" + statusDescripcion + '\'' +
                '}';
    }

}
