package com.flashmeet.messages.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageServiceResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private String errorCode;
    private int statusCode;
    private String statusDescripcion;

    public static <T> MessageServiceResponse<T> success(String message, T data, HttpStatus httpStatus) {
        return new MessageServiceResponse<T>(true, message, data, null, httpStatus.value(),
                httpStatus.getReasonPhrase());
    }

    public static <T> MessageServiceResponse<T> failure(String message, T data, HttpStatus httpStatus,
            String errorCode) {
        return new MessageServiceResponse<T>(false, message, data, errorCode, httpStatus.value(),
                httpStatus.getReasonPhrase());
    }

    @Override
    public String toString() {
        return "MessageServiceResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", errorCode='" + errorCode + '\'' +
                ", statusCode=" + statusCode +
                ", statusDescripcion='" + statusDescripcion + '\'' +
                '}';
    }

}
