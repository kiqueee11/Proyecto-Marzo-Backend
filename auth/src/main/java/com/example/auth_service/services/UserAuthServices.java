package com.example.auth_service.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth_service.exceptions.UserAuthException;
import com.example.auth_service.interfaces.UserServiceClient;
import com.example.auth_service.model.UserAuthData;
import com.example.auth_service.repository.UserAuthRepository;

import lombok.Data;

@Service
@Data
public class UserAuthServices {

    private UserAuthRepository userAuthRepository;
    private UserServiceClient userServiceClient;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserAuthServices(UserAuthRepository userAuthRepository, UserServiceClient userServiceClient) {
        this.userAuthRepository = userAuthRepository;
        this.userServiceClient = userServiceClient;
    }

    public UserAuthData creaAuthData(String name, String password, String email, String image1, String image2,
            String image3, String image4, String image5, String image6) {

                Optional<ResponseEntity<Map<String, Object>>> userData = userServiceClient.getUserByEmail(email);
                String emailString = userData.get().getBody().get("email").toString();
        if (email.equals(emailString)) {

            throw new UserAuthException("USER_ALREADY_EXISTS:USER_ERROR");

        } else {
             Optional<ResponseEntity<Map<String, Object>>> newUserData = userServiceClient.createUser(name, password, email, image1, image2,
                    image3, image4, image5, image6);
            String encodedPassword = passwordEncoder.encode(password);
            UserAuthData userAuthData = new UserAuthData();
            userAuthData.setEmail(email);
            userAuthData.setName(name);
            
            userAuthData.setPassword(encodedPassword);
            return userAuthRepository.save(userAuthData);
        }

    }

}
