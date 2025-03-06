package com.example.auth_service.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tokens")
public class AuthTokens {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Instant tokenExpirationDate;

    @Column(unique = true, nullable = false)
    private String tokenUID;

    @Column(nullable = false)
    private String userUID;

    @Column(nullable = false)
    private boolean isRevoked = false;

    @Column(nullable = false, unique = true, length = 500)
    private String token;

}
