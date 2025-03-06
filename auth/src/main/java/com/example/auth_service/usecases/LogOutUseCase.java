package com.example.auth_service.usecases;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.auth_service.exceptions.UserAuthException;
import com.example.auth_service.model.AuthTokens;
import com.example.auth_service.service_components.TokenDataManagerService;
import com.example.auth_service.services.TokenExpirationNotificationService;

@Service
public class LogOutUseCase {

    private TokenDataManagerService tokenDataManager;
    private TokenExpirationNotificationService tokenExpirationNotificationService;

    public LogOutUseCase(TokenDataManagerService tokenDataManager,
            TokenExpirationNotificationService tokenExpirationNotificationService) {
        this.tokenDataManager = tokenDataManager;
        this.tokenExpirationNotificationService = tokenExpirationNotificationService;
    }

    public void execute(String userUID) {

        AuthTokens authTokens = tokenDataManager.getTokens(userUID).stream()
                .filter(token -> token.isRevoked() == false).findFirst().orElse(null);

        if (authTokens == null) {
            throw new UserAuthException("THERE_IS_NO_VALID_TOKEN_TO_REVOKE",
                    "No hay token valido que cancelar, este usuario no tiene ninguna sesion activa",
                    HttpStatus.BAD_REQUEST);
        }

        authTokens.setRevoked(true);
        tokenDataManager.save(authTokens);
        tokenExpirationNotificationService.notifyListeners(userUID);

    }

}
