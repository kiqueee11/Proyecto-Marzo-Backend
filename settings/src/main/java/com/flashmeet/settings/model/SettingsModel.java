package com.flashmeet.settings.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "settings")
public class SettingsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;


    @Column(nullable = false)
    private int maxAge;

    @Column(nullable = false)
    private int minAge;

    @Column(nullable = false)

    private int distance;

    @Column(nullable = false)

    private String sexualPreference;

    @Column(nullable = false)

    private boolean isVisible = true;
}