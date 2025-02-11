package com.example.auth_service.controller;

import java.time.Instant;
import java.time.ZonedDateTime;

import org.springframework.data.geo.Point;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_service.model.Autenticacion;
import com.example.auth_service.services.UserAuthServices;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class UserAuthDataController {

    private UserAuthServices userAuthServices;

    public UserAuthDataController(UserAuthServices userAuthServices) {
        this.userAuthServices = userAuthServices;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> createAuthData(@RequestParam String nombre, @RequestParam String clave,
            @RequestParam String email, @RequestParam String image1, @RequestParam String image2,
            @RequestParam String image3, @RequestParam String image4, @RequestParam String image5,
            @RequestParam String image6, @RequestParam String sexo,@RequestParam String descripcion,@RequestParam String fechaNacimiento,@RequestParam String posicion) {


        Autenticacion userAuthData = userAuthServices.creaAuthData(nombre, clave, email, image1, image2, image3,
                image4, image5, image6,sexo,descripcion,fechaNacimiento,posicion);


        return ResponseEntity.ok().body(userAuthData);
    }

}
