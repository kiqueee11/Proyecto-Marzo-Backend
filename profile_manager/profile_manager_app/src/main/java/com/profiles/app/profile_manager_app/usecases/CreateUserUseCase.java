package com.profiles.app.profile_manager_app.usecases;

import java.time.ZonedDateTime;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import com.profiles.app.profile_manager_app.models.DatosUsuario;
import com.profiles.app.profile_manager_app.repository.UserRepository;
import com.profiles.app.profile_manager_app.utils.PointParser;

@Service
public class CreateUserUseCase {

    private UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(String idUsuario, String nombre, String email, String imagen1, String imagen2,
            String imagen3, String imagen4, String imagen5, String imagen6, String sexo, String descripcion,
            String fechaNacimiento, String posicion) {

        try {
            ZonedDateTime zdt = ZonedDateTime.parse(fechaNacimiento);
            java.time.Instant fechaNacimientoInstant = zdt.toInstant();
            DatosUsuario user = new DatosUsuario();
            user.setIdUsuario(idUsuario);
            user.setNombre(nombre);
            user.setEmail(email);
            user.setImagen1(imagen1);
            user.setImagen2(imagen2);
            user.setImagen3(imagen3);
            user.setImagen4(imagen4);
            user.setImagen5(imagen5);
            user.setImagen6(imagen6);
            user.setSexo(sexo);
            user.setDescripcion(descripcion);
            user.setFechaNacimiento(fechaNacimientoInstant);
            user.setPosicion(PointParser.parsePoint(posicion));
            userRepository.save(user);
        } catch (Exception e) {
            throw e;
        }

    }

}
