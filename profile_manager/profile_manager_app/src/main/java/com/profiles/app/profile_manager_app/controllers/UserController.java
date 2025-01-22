package com.profiles.app.profile_manager_app.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.profiles.app.profile_manager_app.models.UserModel;
import com.profiles.app.profile_manager_app.services.LanguagesServices;
import com.profiles.app.profile_manager_app.services.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;
    LanguagesServices languagesServices;

    public UserController(UserService userService, LanguagesServices languagesServices) {
        this.userService = userService;
        this.languagesServices = languagesServices;
    }

    @PostMapping("/internal/create-user")
    public ResponseEntity<UserModel> createUser(
        @RequestParam String userName, @RequestParam String password,
        @RequestParam String email, @RequestParam String image1, @RequestParam String image2,
        @RequestParam String image3, @RequestParam String image4, @RequestParam String image5,
        @RequestParam String image6) {
        UserModel user = new UserModel();
        user.setUsername(userName);
        user.setEmail(email);
        user.setPassword(password);
        user.setImage1(image1);
        user.setImage2(image2);
        user.setImage3(image3);
        user.setImage4(image4);
        user.setImage5(image5);
        user.setImage6(image6);
        userService.createUser(user);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/internal/get-user")
    public ResponseEntity<Map<String, Object>> getUserByEmail(@RequestParam String email) {
        Optional<UserModel> user = this.userService.findUserByEmail(email);

        if(user.isPresent()){
            return ResponseEntity.ok(Map.of("email", user.get().getEmail()));
        }else{
            return  ResponseEntity.ok(Map.of("email", "NOT_FOUND"));
        }

       
    }

}
