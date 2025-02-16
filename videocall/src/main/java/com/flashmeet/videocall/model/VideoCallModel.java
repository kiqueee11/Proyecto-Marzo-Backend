package com.flashmeet.videocall.model;

import java.sql.Date;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "videoCall")
public class VideoCallModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_videollamada;
    private String id_emisor;
    private String id_receptor;
    private Date fecha_inicio;
    private Date fecha_fin;

}