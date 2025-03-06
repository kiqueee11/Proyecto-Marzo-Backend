package com.example.auth_service.componentes.JWT;

import java.security.Signature;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Base64;
import java.util.Base64.Decoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Component;

import com.example.auth_service.CustomUserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class CreadorTokenJWT {

  @Value("${jwt.secret}")
  private String CLAVE_SECRETA;
  private int MAX_TOKEN_DURATION = 600;
  private int MAX_REFRESH_TOKEN_DURATION = 600;

  public Map<String, String> generateToken(Authentication autentication, String userRol) {

    Decoder decoder = Base64.getDecoder();
    Map<String, String> resultMap = new HashMap<>();
    byte[] claveDecodificada = decoder.decode(CLAVE_SECRETA);
    String rol = userRol;
    CustomUserDetails userDetails = (CustomUserDetails) autentication.getPrincipal();
    String tokenUID = UUID.randomUUID().toString();
    String userUID = userDetails.getUserId();
    boolean isRefreshToken = false;
    ZonedDateTime issuedAt = ZonedDateTime.now();
    ZonedDateTime expirationDate = ZonedDateTime.now().plusSeconds(MAX_TOKEN_DURATION);
    String token = Jwts.builder().claim("rol", rol).subject(userDetails.getUsername())

        .claim("userId", userDetails.getUserId())
        .claim("isRefreshToken", isRefreshToken)
        .issuedAt(Date.from(issuedAt.toInstant())).id(UUID.randomUUID().toString())
        .expiration(Date.from(expirationDate.toInstant())).signWith(Keys.hmacShaKeyFor(claveDecodificada))
        .compact();

    resultMap.put("token", token);
    resultMap.put("tokenUID", tokenUID);
    resultMap.put("isRefreshToken", String.valueOf(isRefreshToken));
    resultMap.put("userUID", userUID);
    resultMap.put("issuedAt", issuedAt.toString());
    resultMap.put("expirationDate", expirationDate.toString());
    return resultMap;

  }

  public Map<String, String> generateRefreshToken(Authentication autentication, String userRol) {

    Decoder decoder = Base64.getDecoder();
    Map<String, String> resultMap = new HashMap<>();
    byte[] claveDecodificada = decoder.decode(CLAVE_SECRETA);
    String rol = userRol;
    CustomUserDetails userDetails = (CustomUserDetails) autentication.getPrincipal();
    String tokenUID = UUID.randomUUID().toString();
    String userUID = userDetails.getUserId();
    boolean isRefreshToken = true;
    ZonedDateTime issuedAt = ZonedDateTime.now();
    ZonedDateTime expirationDate = ZonedDateTime.now().plusSeconds(MAX_REFRESH_TOKEN_DURATION);
    String token = Jwts.builder().claim("rol", rol).subject(userDetails.getUsername())

        .claim("userId", userDetails.getUserId())
        .claim("isRefreshToken", isRefreshToken)
        .issuedAt(Date.from(issuedAt.toInstant())).id(UUID.randomUUID().toString())
        .expiration(Date.from(expirationDate.toInstant())).signWith(Keys.hmacShaKeyFor(claveDecodificada))
        .compact();

    resultMap.put("token", token);
    resultMap.put("tokenUID", tokenUID);
    resultMap.put("isRefreshToken", String.valueOf(isRefreshToken));
    resultMap.put("userUID", userUID);
    resultMap.put("issuedAt", issuedAt.toString());
    resultMap.put("expirationDate", expirationDate.toString());

    return resultMap;

  }
}
