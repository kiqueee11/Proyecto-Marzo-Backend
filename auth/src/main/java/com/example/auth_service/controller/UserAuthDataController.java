package com.example.auth_service.controller;

import java.time.Instant;
import java.time.ZonedDateTime;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.data.geo.Point;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_service.DTOs.AuthDTO;
import com.example.auth_service.DTOs.LoginDto;
import com.example.auth_service.model.Autenticacion;
import com.example.auth_service.response.AuthenticationResponse;
import com.example.auth_service.services.UserAuthServices;

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
    public ResponseEntity<AuthenticationResponse<AuthDTO>> createAuthData(@RequestParam String nombre,
            @RequestParam String clave,
            @RequestParam String email, @RequestParam String image1, @RequestParam String image2,
            @RequestParam String image3, @RequestParam String image4, @RequestParam String image5,
            @RequestParam String image6, @RequestParam String sexo, @RequestParam String descripcion,
            @RequestParam String fechaNacimiento, @RequestParam String posicion) {

        AuthDTO authDTO = userAuthServices.creaAuthData(nombre, clave, email, image1, image2, image3,
                image4, image5, image6, sexo, descripcion, fechaNacimiento, posicion);

        AuthenticationResponse<AuthDTO> authenticationResponse = new AuthenticationResponse<AuthDTO>(true,
                "USUARIO_CREADO_CORRECTAMENTE", authDTO, HttpStatus.CREATED.value(),
                HttpStatus.CREATED.getReasonPhrase());

        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponse);
    }

    @PostMapping("/iniciarSesion")
    public ResponseEntity<AuthenticationResponse<AuthDTO>> iniciarSesion(@RequestParam String email,
            @RequestParam String clave) {

        LoginDto loginDto = userAuthServices.iniciarSesion(email, clave);
        AuthDTO authDTO = new AuthDTO(null, loginDto.userId(),
                loginDto.token());
        AuthenticationResponse<AuthDTO> authenticationResponse = new AuthenticationResponse<AuthDTO>(true,
                "SESION_INICIADA_CORRECTAMENTE", authDTO, HttpStatus.OK.value(),
                HttpStatus.CREATED.getReasonPhrase());

        return ResponseEntity.status(HttpStatus.OK).body(authenticationResponse);
    }

    @PostMapping("/cerrarSesion")
    public String cerrarSesion(@RequestBody String entity) {

        return entity;
    }

}
