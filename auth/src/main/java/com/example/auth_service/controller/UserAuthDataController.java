package com.example.auth_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.auth_service.model.UserAuthData;
import com.example.auth_service.services.UserAuthServices;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/api/auth")
public class UserAuthDataController {

    private UserAuthServices userAuthServices;

    public UserAuthDataController(UserAuthServices userAuthServices) {
        this.userAuthServices = userAuthServices;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> createAuthData(@RequestParam String userName, @RequestParam String password, @RequestParam String email) {

       
        UserAuthData userAuthData = userAuthServices.creaAuthData(userName, password, email);

        return ResponseEntity.ok().body(userAuthData);
    }

}
