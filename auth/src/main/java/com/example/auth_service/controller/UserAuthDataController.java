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

import com.example.auth_service.DTOs.AuthDTO;
import com.example.auth_service.DTOs.LoginDto;
import com.example.auth_service.model.Autenticacion;
import com.example.auth_service.response.AuthenticationResponse;
import com.example.auth_service.services.UserAuthServices;
import com.example.auth_service.validator.annotations.EmailValidatorAnnotation;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class UserAuthDataController {

    private UserAuthServices userAuthServices;

    public UserAuthDataController(UserAuthServices userAuthServices) {
        this.userAuthServices = userAuthServices;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse<AuthDTO>> signup(@RequestParam @NonNull String nombre,
            @RequestParam @NonNull String clave,
            @RequestParam @NonNull @Valid @EmailValidatorAnnotation String email, @RequestParam String image1,
            @RequestParam String image2,
            @RequestParam String image3, @RequestParam String image4, @RequestParam String image5,
            @RequestParam String image6, @RequestParam String sexo, @RequestParam String descripcion,
            @RequestParam String fechaNacimiento, @RequestParam String posicion) {
System.out.println("hola");
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

        return ResponseEntity.status(authenticationResponse.getStatusCode()).body(authenticationResponse);
    }

    @PostMapping("/cerrarSesion")
    public String cerrarSesion(@RequestBody String entity) {

        return entity;
    }

}
