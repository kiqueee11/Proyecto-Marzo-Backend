package com.profiles.app.profile_manager_app.DTOs;

import java.time.Instant;

import lombok.Data;

@Data

public class DatosUsuarioDTO {

    String idUsuario;
    String sexo;
    Instant fechaNacimiento;
    String descripcion;

    public DatosUsuarioDTO(String idUsuario, String sexo, Instant fechaNacimiento, String descripcion) {
        this.idUsuario = idUsuario;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.descripcion = descripcion;
    }

}
