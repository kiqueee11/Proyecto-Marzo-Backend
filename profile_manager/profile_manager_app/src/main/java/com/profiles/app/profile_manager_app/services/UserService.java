package com.profiles.app.profile_manager_app.services;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.WKTWriter;

import com.profiles.app.profile_manager_app.DTOs.DatosUsuarioDTO;
import com.profiles.app.profile_manager_app.Exceptions.UserException;
import com.profiles.app.profile_manager_app.models.DatosUsuario;
import com.profiles.app.profile_manager_app.repository.UserRepository;
import com.profiles.app.profile_manager_app.utils.PointParser;
import com.profiles.app.profile_manager_app.validators.ValidadorDatosUsuario;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Create a new user
     * 
     * @param idUsuario
     * @param nombre
     * @param email
     * @param imagen1
     * @param imagen2
     * @param imagen3
     * @param imagen4
     * @param imagen5
     * @param imagen6
     * @param sexo
     * @param descripcion
     * @param fechaNacimiento
     * @param posicion
     * @throws UserException
     * @throws IllegalArgumentException
     * @throws OptimisticLockingFailureException
     * @returns UserModel
     * 
     **/

    public DatosUsuario createUser(String idUsuario, String nombre, String email, String imagen1, String imagen2,
            String imagen3, String imagen4, String imagen5, String imagen6, String sexo, String descripcion,
            String fechaNacimiento, String posicion) {

        try {
            ValidadorDatosUsuario.validarDatosUsuario(nombre, idUsuario, email, imagen1, imagen2, imagen3, imagen4,
                    imagen5, imagen6, sexo, descripcion, fechaNacimiento, posicion, userRepository);

            ZonedDateTime zdt = ZonedDateTime.parse(fechaNacimiento);
            java.time.Instant fechaNacimientoInstant = zdt.toInstant();
            Point posicionPoint = PointParser.parsePoint(posicion);
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
            user.setPosicion(posicionPoint);
            user = userRepository.save(user);

            return user;
        } catch (RuntimeException e) {
            throw new UserException(e.getMessage());
        }

    }

    /**
     * Find a user by its email.
     * 
     * @param email The email of the user to find.
     * @return The user with the given email, or null if no such user exists.
     */

    public Optional<DatosUsuario> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public ResponseEntity<?> buscarUsuarioPorPosicion(String posicion, int distancia) {

        try {
            Point posPoint = PointParser.parsePoint(posicion);
            WKTWriter wktWriter = new WKTWriter();
            String posString = wktWriter.write(posPoint);
            Optional<ArrayList<DatosUsuarioDTO>> usuarios = userRepository.findByPosicion(posString, distancia);
            if (usuarios.isPresent()) {
                return ResponseEntity.ok(usuarios.get());
            }
            return ResponseEntity.notFound().build();

        } catch (Exception e) {

            throw new UserException(e.getMessage());
        }
    }

    public Optional<DatosUsuario> getUserById(String id) {
        return userRepository.findById(Long.parseLong(id));
    }


}
