package com.example.auth_service.interfaces;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.geo.Point;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "userservice")
public interface UserServiceClient {

    @GetMapping(value = "/users/internal/get-user")
    Optional<ResponseEntity<Map<String, Object>>> getUserByEmail(@RequestParam String email);

    @PostMapping(value = "/users/internal/create-user")
    Optional<ResponseEntity<Map<String, Object>>> createUser(@RequestParam String uidString,
            @RequestParam String userName,
            @RequestParam String email, @RequestParam String image1, @RequestParam String image2,
            @RequestParam String image3, @RequestParam String image4, @RequestParam String image5,
            @RequestParam String image6, @RequestParam String sexo,@RequestParam String descripcion,@RequestParam String fechaNacimiento,@RequestParam String posicion);

}
