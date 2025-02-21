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

    private String id_usuario;


    @Column(nullable = false)
    private int edad_maxima;

    @Column(nullable = false)
    private int edad_minima;

    @Column(nullable = false)

    private int distancia;

    @Column(nullable = false)

    private String preferencia_sexual;

    @Column(nullable = false)

    private boolean visible = true;
}