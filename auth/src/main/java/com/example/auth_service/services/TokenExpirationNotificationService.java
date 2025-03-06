package com.example.auth_service.services;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TokenExpirationNotificationService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public TokenExpirationNotificationService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void notifyListeners(String userUID) {
        kafkaTemplate.send("token-expiration-notification", userUID);
    }

}
