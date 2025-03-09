package com.profiles.app.profile_manager_app.DTOs;

import java.time.Instant;
import java.util.List;

import lombok.Data;

@Data

public class DatosUsuarioDTO {
    String nombre;
    String idUsuario;
    String sexo;
    Instant fechaNacimiento;
    String descripcion;
    List<String> imagenes;

    public DatosUsuarioDTO(String nombre,String idUsuario, String sexo, Instant fechaNacimiento, String descripcion,
            List<String> imagenes) {
                this.nombre = nombre;
        this.idUsuario = idUsuario;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
    }

}