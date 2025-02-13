package com.example.auth_service.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Version;





/**
 * Representa la información de autenticación de un usuario.
 * Esta clase se mapea a la tabla 'autenticacion' en la base de datos.
 *
 */
@Entity
@Table(name = "autenticacion")
@Data
public class Autenticacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)

    private long Id;
    @Column(length = 50, unique = false, nullable = false)
    private String name;
    @Column(length = 255, unique = true, nullable = false)
    private String clave;
    @Column(length = 255, unique = true, nullable = false)
    private String email;
    @Column(length = 50, unique = true, nullable = false)
    private String idUsuario;

    @Version
    private Long version;

    @CreationTimestamp
    private Date created_at;

    @UpdateTimestamp
    private Date updated_at;

}
