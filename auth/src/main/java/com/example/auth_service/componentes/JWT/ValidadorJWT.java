package com.example.auth_service.componentes.JWT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class ValidadorJWT {

    @Value("${jwt.secret}")
    private String CLAVE_SECRETA;

    public boolean validarToken(String token) {
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor((CLAVE_SECRETA.getBytes()))).build().parseSignedClaims(token);
            return true;

        } catch (Exception e) {

            return false;
        }
    }

}
