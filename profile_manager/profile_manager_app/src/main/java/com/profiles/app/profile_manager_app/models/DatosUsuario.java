package com.profiles.app.profile_manager_app.models;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.geo.Point;

import com.profiles.app.profile_manager_app.imported_models.UserAuthData;

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

    public DatosUsuario(String idUsuario, String nombre, String email, String imagen1, String imagen2, String imagen3,
            String imagen4, String imagen5, String imagen6, String descripcion,String sexo, Instant fechaNacimiento, Point posicion) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.imagen3 = imagen3;
        this.imagen4 = imagen4;
        this.imagen5 = imagen5;
        this.imagen6 = imagen6;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.posicion = posicion;
        this.descripcion=descripcion;
    }

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
    private boolean istaActivo = false;

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

    @Column(nullable = true)
    private Point posicion;

    @Version
    private Long version;

    @CreationTimestamp
    private Date created_at;

    @UpdateTimestamp
    private Date updated_at;
}
