package com.example.auth_service.services;

import org.springframework.stereotype.Service;

import com.example.auth_service.usecases.LoginUseCase;
import com.example.auth_service.usecases.SignUpUseCase;

import lombok.Getter;

@Service
@Getter
public class UserAuthServices {

    public SignUpUseCase signUpUseCase;
    public LoginUseCase loginUseCase;

    public UserAuthServices(SignUpUseCase signUpUseCase, LoginUseCase loginUseCase) {
        this.signUpUseCase = signUpUseCase;
        this.loginUseCase = loginUseCase;
    }

}
