package com.example.auth_service.controller;

import java.time.Instant;
import java.time.ZonedDateTime;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.data.geo.Point;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.auth_service.DTOs.AuthDTO;
import com.example.auth_service.DTOs.LoginDto;
import com.example.auth_service.response.AuthenticationResponse;
import com.example.auth_service.services.UserAuthServices;
import com.example.auth_service.validator.annotations.EmailValidatorAnnotation;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class UserAuthDataController {

    private final UserAuthServices userAuthServices;

    public UserAuthDataController(UserAuthServices userAuthServices) {
        this.userAuthServices = userAuthServices;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse<AuthDTO>> signup(@RequestParam @NonNull String nombre,
            @RequestParam @NonNull String clave,
            @RequestParam @NonNull @Valid @EmailValidatorAnnotation String email, @RequestParam("image1") MultipartFile image1,
            @RequestParam("image2") MultipartFile image2,
            @RequestParam("image3") MultipartFile image3, @RequestParam("image4") MultipartFile image4, @RequestParam("image5") MultipartFile image5,
            @RequestParam("image6") MultipartFile image6, @RequestParam String sexo, @RequestParam String descripcion,
            @RequestParam String fechaNacimiento, @RequestParam String posicion) {
        userAuthServices.signUpUseCase.execute(nombre, clave, email, image1, image2, image3,
                image4, image5, image6, sexo, descripcion, fechaNacimiento, posicion);

        AuthenticationResponse<AuthDTO> authenticationResponse = AuthenticationResponse.success(true, "SUCCESS", null,
                HttpStatus.CREATED);

        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponse);
    }

    @PostMapping("/iniciarSesion")
    public ResponseEntity<AuthenticationResponse<LoginDto>> iniciarSesion(@RequestParam String email,
            @RequestParam String clave) {

        LoginDto loginDto = userAuthServices.loginUseCase.execute(email, clave);
        AuthenticationResponse<LoginDto> authenticationResponse = AuthenticationResponse.success(true,
                "SESION_INICIADA_CORRECTAMENTE", loginDto, HttpStatus.OK);

        // Devuelvo el resultado o respuesta de la request
        return ResponseEntity.status(authenticationResponse.getStatusCode()).body(authenticationResponse);
    }

    @PostMapping("/cerrarSesion")
    public ResponseEntity<AuthenticationResponse<LoginDto>> cerrarSesion(@RequestParam("userId") String userId) {
        userAuthServices.logOutUseCase.execute(userId);
        AuthenticationResponse<LoginDto> authenticationResponse = AuthenticationResponse.success(true,
                "SESSION_CERRADA_CORRECTAMENTE", null, HttpStatus.OK);
        return ResponseEntity.status(authenticationResponse.getStatusCode()).body(authenticationResponse);
    }

}
