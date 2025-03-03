package com.flashmeet.gateway.gateway.componentes.JWT;

import java.security.Key;
import java.util.List;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;

public class DecodificarToken {

    @Value("${jwt.secret}")
    private String CLAVE_SECRETA;

    // Extraer el nombre de usuario del token
    public String getUsernameFromToken(String token) {
        JwtParserBuilder parser = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(Base64.decode(CLAVE_SECRETA)));
        Claims claims = parser.build().parseSignedClaims(token).getPayload();
        return claims.getSubject();
    }

    @SuppressWarnings("unchecked")
    // Extraer las autoridades del token
    public List<GrantedAuthority> getAuthoritiesFromToken(String token) {
        Claims claimns = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(Base64.decode(CLAVE_SECRETA))).build()
                .parseSignedClaims(token).getPayload();
        String rol = claimns.get("rol", String.class);
        SimpleGrantedAuthority authorities = new SimpleGrantedAuthority(rol);

        return List.of(authorities);

    }

    public String getUserIdFromToken(String token) {
        JwtParserBuilder parser = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(Base64.decode(CLAVE_SECRETA)));
        Claims claims = parser.build().parseSignedClaims(token).getPayload();
        return (String) claims.get("userId");
    }

}
