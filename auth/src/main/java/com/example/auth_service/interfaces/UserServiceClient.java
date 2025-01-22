package com.example.auth_service.interfaces;

import java.util.Map;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:8081", name = "user-service")
public interface UserServiceClient {

    @GetMapping(value = "/users/internal/get-user")
    Optional<ResponseEntity<Map<String, Object>>>getUserByEmail(@RequestParam String email);
    @PostMapping(value = "/users/internal/create-user")
    Optional<ResponseEntity<Map<String, Object>>> createUser(@RequestParam String userName, @RequestParam String password,
    @RequestParam String email, @RequestParam String image1, @RequestParam String image2,
    @RequestParam String image3, @RequestParam String image4, @RequestParam String image5,
    @RequestParam String image6);
    
    
}
