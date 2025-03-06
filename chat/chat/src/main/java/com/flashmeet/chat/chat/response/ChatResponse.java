package com.flashmeet.chat.chat.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private String errorCode;
    private int statusCode;
    private String statusDescripcion;

    public static <T> ChatResponse<T> success(String message, T data, HttpStatus httpStatus) {
        return new ChatResponse<T>(true, message, data, null, httpStatus.value(), httpStatus.getReasonPhrase());
    }

    public static <T> ChatResponse<T> failure(String message, T data, HttpStatus httpStatus, String errorCode) {
        return new ChatResponse<T>(false, message, data, errorCode, httpStatus.value(), httpStatus.getReasonPhrase());
    }

    @Override
    public String toString() {
        return "ChatResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", errorCode='" + errorCode + '\'' +
                ", statusCode=" + statusCode +
                ", statusDescripcion='" + statusDescripcion + '\'' +
                '}';
    }

}
