package com.profiles.app.profile_manager_app.models;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.locationtech.jts.geom.*;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.profiles.app.profile_manager_app.serializadores.coordenadas.SerializadorMapaPoint;
import com.profiles.app.profile_manager_app.serializadores.coordenadas.SerializadorPointMapa;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class DatosUsuario {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 15)
    private String idUsuario;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 255)
    private String imagen1;
    private String imagen2;
    private String imagen3;
    private String imagen4;
    private String imagen5;
    private String imagen6;

    @Column(nullable = false)
    private boolean estaActivo = false;

    @Column(nullable = false)
    private boolean estaBloqueado = false;

    @Column(nullable = true, length = 250)
    private String descripcion;

    @Column(nullable = false)
    private Long ultimaInteraccionMs = (long) 0;

    @Column(nullable = false)
    private Instant fechaNacimiento;

    @Column(nullable = false)
    private String sexo;

    @JsonSerialize(using=SerializadorPointMapa.class)
    @JsonDeserialize(using=SerializadorMapaPoint.class)
    @Column(nullable = false)

    private Point posicion;

    @Version
    private Long version;

    @CreationTimestamp
    private Date created_at;

    @UpdateTimestamp
    private Date updated_at;
}
