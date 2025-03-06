package com.profiles.app.profile_manager_app.ExceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.profiles.app.profile_manager_app.DTOs.DatosUsuarioDTO;
import com.profiles.app.profile_manager_app.Exceptions.ErrCodes;
import com.profiles.app.profile_manager_app.Exceptions.UserException;
import com.profiles.app.profile_manager_app.response.UserServiceResponse;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<UserServiceResponse<DatosUsuarioDTO>> handleUserException(UserException e) {
        UserServiceResponse<DatosUsuarioDTO> userServiceResponse;

        userServiceResponse = UserServiceResponse.failure("ERROR", null, e.getHttpStatus(), e.getErrorCode());

        return ResponseEntity.status(userServiceResponse.getStatusCode()).body(userServiceResponse);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<UserServiceResponse<DatosUsuarioDTO>> handleGenericException(Exception e) {
        UserServiceResponse<DatosUsuarioDTO> userServiceResponse;

        userServiceResponse = UserServiceResponse.failure("ERROR", null, HttpStatus.INTERNAL_SERVER_ERROR, ErrCodes.INTERNAL_SERVER_ERROR);

        return ResponseEntity.status(userServiceResponse.getStatusCode()).body(userServiceResponse);
    }



}
