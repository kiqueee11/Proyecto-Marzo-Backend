package com.example.auth_service.componentes.JWT;

import java.security.Signature;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.auth_service.CustomUserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class CreadorTokenJWT {

  @Value("${jwt.secret}")
  private String CLAVE_SECRETA;

  public String generarToken(Authentication autentication, String userRol) {

    Decoder decoder = Base64.getDecoder();

    byte[] claveDecodificada = decoder.decode(CLAVE_SECRETA);
    String rol = userRol;
    CustomUserDetails userDetails = (CustomUserDetails) autentication.getPrincipal();

    return Jwts.builder().claim("rol", rol).subject(userDetails.getUsername()).claim("userId", userDetails.getUserId())
        .claim("isRefreshToken", false)
        .issuedAt(new Date()).id(UUID.randomUUID().toString()) 
        .expiration(Date.from(Instant.now().plusSeconds(3600))).signWith(Keys.hmacShaKeyFor(claveDecodificada))
        .compact();

  }

  public String generarRefreshToken(Authentication autentication, String userRol) {

    Decoder decoder = Base64.getDecoder();

    byte[] claveDecodificada = decoder.decode(CLAVE_SECRETA);
    String rol = userRol;
    CustomUserDetails userDetails = (CustomUserDetails) autentication.getPrincipal();

    return Jwts.builder().claim("rol", rol).subject(userDetails.getUsername()).claim("userId", userDetails.getUserId())
        .claim("isRefreshToken", true)
        .issuedAt(new Date()).id(UUID.randomUUID().toString()) 
        .expiration(Date.from(Instant.now().plusSeconds(86400))).signWith(Keys.hmacShaKeyFor(claveDecodificada))
        .compact();

  }
}
