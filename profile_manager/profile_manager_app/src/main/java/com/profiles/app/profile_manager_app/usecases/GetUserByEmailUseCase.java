package com.profiles.app.profile_manager_app.usecases;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.profiles.app.profile_manager_app.DTOModelParser.DatoUsuarioModdelToDTO;
import com.profiles.app.profile_manager_app.DTOs.DatosUsuarioDTO;
import com.profiles.app.profile_manager_app.models.DatosUsuario;
import com.profiles.app.profile_manager_app.repository.UserRepository;

@Service
public class GetUserByEmailUseCase {

    private UserRepository userRepository;

    public GetUserByEmailUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public DatosUsuarioDTO execute(String email) {

        Optional<DatosUsuario> datosUsuario = userRepository.findByEmail(email);
        if (datosUsuario.isEmpty()) {
            return null;
        }

        DatosUsuarioDTO datosUsuarioDTO = DatoUsuarioModdelToDTO.parse(datosUsuario.get());
        return datosUsuarioDTO;

    }

}
