package com.example.auth_service.DTOs;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import lombok.Getter;

public record LoginDto(String userId, String token, String refreshToken, String tokenUID, String refreshTokenUId,Instant tokenExpiration, Instant refreshTokenExpiration) {

}
