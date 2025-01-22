package com.example.auth_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth_service.model.UserAuthData;
import com.example.auth_service.repository.UserAuthRepository;

import lombok.Data;

@Service
@Data
public class UserAuthServices {

    private UserAuthRepository userAuthRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserAuthServices(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    public UserAuthData creaAuthData(String name, String password, String email) {
        String encodedPassword = passwordEncoder.encode(password);
        UserAuthData userAuthData = new UserAuthData();
        userAuthData.setEmail(email);
        userAuthData.setPassword(encodedPassword);
        return userAuthRepository.save(userAuthData);
    }

}
