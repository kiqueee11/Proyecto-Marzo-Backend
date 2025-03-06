package com.example.auth_service.usecases;

import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.auth_service.CustomUserDetails;
import com.example.auth_service.DTOs.LoginDto;
import com.example.auth_service.componentes.JWT.CreadorTokenJWT;
import com.example.auth_service.model.AuthTokens;
import com.example.auth_service.service_components.TokenDataManagerService;
import com.example.auth_service.services.TokenExpirationSchedulerService;

@Service
public class LoginUseCase {

    private final AuthenticationManager authenticationManager;
    private final CreadorTokenJWT creadorTokenJWT;
    private final TokenExpirationSchedulerService tokenExpirationService;
    private final TokenDataManagerService tokenDataManager;

    public LoginUseCase(AuthenticationManager authenticationManager, CreadorTokenJWT creadorTokenJWT,

            TokenExpirationSchedulerService tokenExpirationService, TokenDataManagerService tokenDataManager) {
        this.authenticationManager = authenticationManager;
        this.creadorTokenJWT = creadorTokenJWT;
        this.tokenDataManager = tokenDataManager;
        this.tokenExpirationService = tokenExpirationService;
    }

    public LoginDto execute(String email, String password) {
        // Autenticamos al usuario
        Authentication autenticacion = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));

        CustomUserDetails userDetails = (CustomUserDetails) autenticacion.getPrincipal();

        // Generamos tanto el token normal como el refresh token
        Map<String, String> tokenData = creadorTokenJWT.generateToken(autenticacion,
                autenticacion.getAuthorities().stream()
                        .findFirst().get().getAuthority());
        Map<String, String> refreshTokenData = creadorTokenJWT.generateToken(autenticacion,
                autenticacion.getAuthorities().stream()
                        .findFirst().get().getAuthority());

        // Creamos el Login DTO (Data Transfer Object)
        // porque debemos mostrarle al usuario datos como el token y refresh token
        LoginDto loginDto = createLoginDto(userDetails, tokenData, refreshTokenData);

        // Buscamos mas tokens en la base de datos que correspondan con este usuario
        List<AuthTokens> authTokens = tokenDataManager.getTokens(loginDto.userId());

        // Buscamos algun token que no haya sido revocado
        AuthTokens authToken = findAnyUserValidToken(authTokens);
        // Si hay alguno valido (sin revocar) entonces lo revocamos y lo guardamos, ya
        // que en nuestra app un usuario puede tener solo una sesion al mismoo tiempo
        if (authToken != null) {
            authToken.setRevoked(true);
            tokenDataManager.save(authToken);
        } else {
            authToken = new AuthTokens();
            authToken.setUserUID(loginDto.userId());
            authToken.setToken(loginDto.token());
            authToken.setTokenUID(loginDto.tokenUID());
            authToken.setTokenExpirationDate(loginDto.tokenExpiration());
            authToken.setRevoked(false);
        }
        // Guardamos el token en cache y base de datos
        tokenDataManager.save(authToken);
        // programamos su invalidacion automatica dependiendo de la duracion del token
        tokenExpirationService.scheduleTokenExpirationNotification(loginDto.userId(), loginDto.tokenExpiration());

        return loginDto;

    }

    private AuthTokens findAnyUserValidToken(List<AuthTokens> tokens) {
        for (AuthTokens authTokens : tokens) {

            if (!authTokens.isRevoked()) {
                authTokens.setRevoked(true);
                return authTokens;

            }

        }

        return null;
    }

    private LoginDto createLoginDto(CustomUserDetails userDetails, Map<String, String> tokenData,
            Map<String, String> refreshTokenData) {

        Instant refreshTokenExpiryDate = (ZonedDateTime.parse(refreshTokenData.get("expirationDate"))).toInstant();
        Instant tokenExpiryDate = (ZonedDateTime.parse(tokenData.get("expirationDate"))).toInstant();
        System.out.println(tokenData.get("expirationDate"));
        return new LoginDto(userDetails.getUserId(), tokenData.get("token"), refreshTokenData.get("token"),
                tokenData.get("tokenUID"), refreshTokenData.get("tokenUID"), tokenExpiryDate, refreshTokenExpiryDate);
    }

}
