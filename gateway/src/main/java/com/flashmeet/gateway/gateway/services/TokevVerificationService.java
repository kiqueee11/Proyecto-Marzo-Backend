package com.flashmeet.gateway.gateway.services;

import java.time.Instant;
import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class TokevVerificationService {

    private RedisTemplate<String, Object> redisTemplate;

    public TokevVerificationService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 
     * 
     * @param token The token to verify
     * 
     * @returns true if the token is valid.
     * 
     *          false if the token is not valid or is already been deleted from the
     *          cache
     */

    public boolean tokenIsValid(String token) {

        Map<Object, Object> entries = redisTemplate.opsForHash().entries(token);
        boolean result = true;
        if (entries.isEmpty()) {
            return false;
        }

        result &= !(isRevoked(Boolean.parseBoolean(entries.get("isRevoked").toString())));
        result &= !isExpired((double) entries.get("expTime"));

        return result;

    }

    private boolean isExpired(double expTime) {

        long seconds = (long) expTime;
        int nanos = (int) ((expTime - seconds) * 1_000_000_000);
        Instant tokenExpirationTime = Instant.ofEpochSecond(seconds, nanos);

        return Instant.now().isAfter(tokenExpirationTime);
    }

    private boolean isRevoked(boolean isRevoked) {

        return isRevoked;

    }

}
