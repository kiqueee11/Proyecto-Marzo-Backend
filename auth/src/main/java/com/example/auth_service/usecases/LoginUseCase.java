package com.example.auth_service.usecases;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.auth_service.CustomUserDetails;
import com.example.auth_service.DTOs.LoginDto;
import com.example.auth_service.componentes.JWT.CreadorTokenJWT;

@Service
public class LoginUseCase {

    private final AuthenticationManager authenticationManager;
    private final CreadorTokenJWT creadorTokenJWT;

    public LoginUseCase(AuthenticationManager authenticationManager, CreadorTokenJWT creadorTokenJWT) {
        this.authenticationManager = authenticationManager;
        this.creadorTokenJWT = creadorTokenJWT;
    }

    public LoginDto execute(String email, String password) {

        Authentication autenticacion = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));

        CustomUserDetails userDetails = (CustomUserDetails) autenticacion.getPrincipal();
        String token = creadorTokenJWT.generarToken(autenticacion, autenticacion.getAuthorities().stream()
                .findFirst().get().getAuthority());
        String refreshToken = creadorTokenJWT.generarRefreshToken(autenticacion, autenticacion.getAuthorities().stream()
                .findFirst().get().getAuthority());
        return new LoginDto(userDetails.getUserId(), token, refreshToken);
    }

}
