package com.flashmeet.messages;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.hc.core5.http.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.flashmeet.messages.DTOs.MessageDTO;
import com.flashmeet.messages.response.MessageServiceResponse;
import com.flashmeet.messages.serializadores.SerializadorListToMap;
import com.github.andrewoma.dexx.collection.HashMap;

@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageServiceResponse<MessageDTO>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonErrors = errrosToJson(methodArgumentNotValidException);
        System.out.println(jsonErrors);

        String message = objectMapper.readTree(jsonErrors).toString();
        System.out.println(message);

        MessageServiceResponse<MessageDTO> messageServiceResponse = MessageServiceResponse.failure(message, null,
                HttpStatus.BAD_REQUEST, null);

        return ResponseEntity.badRequest().body(messageServiceResponse);

    }

























    

    private String errrosToJson(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<FieldError> errors = methodArgumentNotValidException.getFieldErrors();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (int i = 0; i < errors.size(); i++) {
            FieldError error = errors.get(i);
            stringBuilder.append("\"" + error.getField() + "\":\"" + error.getDefaultMessage() + "\"");
            if (i < errors.size() - 1)
                stringBuilder.append(",");

        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }

}
