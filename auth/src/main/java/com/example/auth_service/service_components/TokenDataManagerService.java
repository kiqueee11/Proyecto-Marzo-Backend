package com.example.auth_service.service_components;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.auth_service.DTOs.LoginDto;
import com.example.auth_service.model.AuthTokens;
import com.example.auth_service.repository.TokensRepository;
import com.example.auth_service.repository.UserAuthRepository;

/**
 * This clas is responsible for Save,Update and delete tokens, and hides the
 * implementations of the cache and the database by only exposing save,update
 * and delete methods
 * 
 * 
 * 
 */

@Service
public class TokenDataManagerService {

    private TokensRepository tokensRepository;
    private RedisTemplate<String, Object> redisTemplate;

    public TokenDataManagerService(TokensRepository tokensRepository, RedisTemplate<String, Object> redisTemplate) {
        this.tokensRepository = tokensRepository;
        this.redisTemplate = redisTemplate;

    }

    public void save(AuthTokens authTokens) {

        saveInDb(authTokens);
        saveInCache(authTokens);

    }

    public List<AuthTokens> getTokens(String uid) {
        Optional<List<AuthTokens>> tokens = tokensRepository.findAllByUserUID(uid);
        if (tokens.isPresent()) {
            return tokens.get();
        } else {
            return null;
        }

    }

    private void saveInDb(AuthTokens authTokens) {

        tokensRepository.save(authTokens);
    }

    private void saveInCache(AuthTokens authTokens) {
        HashMap<String, Object> value = new HashMap<>();
        value.put("userUID", authTokens.getUserUID());
        value.put("tokenUID", authTokens.getTokenUID());
        value.put("expTime", authTokens.getTokenExpirationDate());
        value.put("isRevoked", authTokens.isRevoked());

        redisTemplate.expire(authTokens.getToken(),
                authTokens.getTokenExpirationDate().toEpochMilli() - Instant.now().toEpochMilli(),
                TimeUnit.SECONDS);
        redisTemplate.opsForHash()
                .putAll(authTokens.getToken(), value);

    }

}
