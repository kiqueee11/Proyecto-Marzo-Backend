package com.flashmeet.settings;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flashmeet.settings.Service.SettingsService;
import com.flashmeet.settings.exceptions.SettingsException;
import com.flashmeet.settings.rersponse.SettingsResponse;

@RestControllerAdvice
public class SettingsExceptionHandler {

    @ExceptionHandler(value = SettingsException.class)
    public ResponseEntity<SettingsResponse<?>> handleUserException(SettingsException e) {
        SettingsResponse<?> settingsResponse;

        settingsResponse = SettingsResponse.failure("ERROR", null, e.getHttpStatus(), e.getErrorCode());

        return ResponseEntity.status(settingsResponse.getStatusCode()).body(settingsResponse);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<SettingsResponse<?>> handleGenericException(Exception e) {
        SettingsResponse<?> settingsResponse;

        settingsResponse = SettingsResponse.failure("ERROR", null, HttpStatus.INTERNAL_SERVER_ERROR,
                "SERVER_INTERNAL_ERROR");

        return ResponseEntity.status(settingsResponse.getStatusCode()).body(settingsResponse);
    }

}
