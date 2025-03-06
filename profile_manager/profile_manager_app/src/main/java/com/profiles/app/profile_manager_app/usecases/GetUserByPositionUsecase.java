package com.profiles.app.profile_manager_app.usecases;

import java.lang.classfile.ClassFile.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.WKTWriter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.profiles.app.profile_manager_app.DTOModelParser.DatoUsuarioModdelToDTO;
import com.profiles.app.profile_manager_app.DTOs.DatosUsuarioDTO;
import com.profiles.app.profile_manager_app.Exceptions.ErrCodes;
import com.profiles.app.profile_manager_app.Exceptions.UserException;
import com.profiles.app.profile_manager_app.models.DatosUsuario;
import com.profiles.app.profile_manager_app.repository.UserRepository;
import com.profiles.app.profile_manager_app.utils.PointParser;

@Service
public class GetUserByPositionUsecase {

    private UserRepository userRepository;

    public GetUserByPositionUsecase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<DatosUsuarioDTO> execute(String posicion, int distancia) {
        List<DatosUsuarioDTO> usuariosDTO = new ArrayList<>();

        try {
            Point posPoint = PointParser.parsePoint(posicion);
            WKTWriter wktWriter = new WKTWriter();
            String posString = wktWriter.write(posPoint);
            Optional<List<DatosUsuario>> usuarios = userRepository.findByPosicion(posString, distancia);
            if (!usuarios.isPresent()) {
                return null;
            }

            usuarios.get().forEach(datos -> {
                usuariosDTO.add(DatoUsuarioModdelToDTO.parse(datos));
            });

            return usuariosDTO;

        } catch (Exception e) {

            throw e;
        }

    }

}
