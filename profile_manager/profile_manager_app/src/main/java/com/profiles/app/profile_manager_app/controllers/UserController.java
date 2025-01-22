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

    @PostMapping("/create")
    public ResponseEntity<UserModel> createUser(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam List<String> speakedLanguages,
            @RequestParam List<String> learningLanguages,
            @RequestParam boolean isUserActive,
            @RequestParam boolean isUserBlocked) {
        UserModel user = new UserModel();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setSpeakedLanguages(speakedLanguages);
        user.setLearningLanguages(learningLanguages);
        user.setUserActive(isUserActive);
        user.setUserBlocked(isUserBlocked);
        userService.createUser(user);

        return ResponseEntity.ok(user) ;
    }

    @GetMapping("/supported-languages")
    public List<String> getSupportedLanguages() {
        return languagesServices.getSupportedLanguages();
    }

}
