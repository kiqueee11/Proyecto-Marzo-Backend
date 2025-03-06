package com.flashmeet.chat.chat;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.flashmeet.chat.chat.exceptions.ChatException;
import com.flashmeet.chat.chat.exceptions.ErrCodes;
import com.flashmeet.chat.chat.model.ChatModel;
import com.flashmeet.chat.chat.response.ChatResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(ChatException.class)
    public ResponseEntity<ChatResponse<ChatModel>> handleChatException(ChatException e) {

        return ResponseEntity.status(e.getHttpStatus())
                .body(ChatResponse.failure(e.getMessage(), null, e.getHttpStatus(), e.getErrorCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ChatResponse<?>> handleGenericException(Exception ex, WebRequest request) {
        // Log the exception (Crucial for debugging!)
        ex.printStackTrace(); // Use a proper logging framework in production!

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ChatResponse.failure("Internal Server Error", null, HttpStatus.INTERNAL_SERVER_ERROR,
                        ErrCodes.INTERNAL_SERVER_ERROR));
    }

}
