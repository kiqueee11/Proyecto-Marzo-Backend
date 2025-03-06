package com.profiles.app.profile_manager_app.DTOModelParser;

import java.util.ArrayList;
import java.util.List;

import com.profiles.app.profile_manager_app.DTOs.DatosUsuarioDTO;
import com.profiles.app.profile_manager_app.models.DatosUsuario;

public class DatoUsuarioModdelToDTO {

    public static DatosUsuarioDTO parse(DatosUsuario datoUsuario) {
        if (datoUsuario == null) {
            return null;
        }

        List<String> images = new ArrayList<>();
        images = List.of(datoUsuario.getImagen1(), datoUsuario.getImagen2(), datoUsuario.getImagen3(),
                datoUsuario.getImagen4(), datoUsuario.getImagen5(), datoUsuario.getImagen6());

        DatosUsuarioDTO datoUsuarioDTO = new DatosUsuarioDTO(datoUsuario.getNombre(),datoUsuario.getIdUsuario(), datoUsuario.getSexo(),
                datoUsuario.getFechaNacimiento(), datoUsuario.getDescripcion(), images);

        return datoUsuarioDTO;
    }

}
