package com.profiles.app.profile_manager_app.usecases;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.profiles.app.profile_manager_app.DTOModelParser.DatoUsuarioModdelToDTO;
import com.profiles.app.profile_manager_app.DTOs.DatosUsuarioDTO;
import com.profiles.app.profile_manager_app.models.DatosUsuario;
import com.profiles.app.profile_manager_app.repository.UserRepository;

@Service
public class GetUserByIdUseCase {

    private UserRepository userRepository;

    public GetUserByIdUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public DatosUsuarioDTO execute(String id) {

        Optional<DatosUsuario> user = userRepository.findByIdUsuario(id);

        if (user.isEmpty()) {
            return null;
        } else {
            return DatoUsuarioModdelToDTO.parse(user.get());
        }

    }

}
