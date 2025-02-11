package com.profiles.app.profile_manager_app.services;

import java.time.ZonedDateTime;
import java.util.Optional;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.locationtech.jts.geom.*;

import com.profiles.app.profile_manager_app.Exceptions.UserException;
import com.profiles.app.profile_manager_app.models.DatosUsuario;
import com.profiles.app.profile_manager_app.repository.UserRepository;

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

            ZonedDateTime zdt = ZonedDateTime.parse(fechaNacimiento);
            java.time.Instant fechaNacimientoInstant = zdt.toInstant();
            Point posicionPoint = parsePoint(posicion);

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
            
            if (imagen1 == null || imagen1.trim().isEmpty()) {
                throw new UserException("AT_LEAST_ONE_IMAGE_SHOULD_BE_ADDED:USER_ERROR");
            }

            user = userRepository.save(user);
            
            return user;
        } catch (RuntimeException e) {
            throw new UserException(e.getMessage());
        }

    }

    private boolean isStringLengthSupported(String string, int maxLength) {
        return string.length() <= maxLength;
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

    /**
     * Generates an unique id with letters and numbers
     * 
     * @param length the length of the UID
     * @return An String UID
     */
    private String generateId(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();

    }

    private Point parsePoint(String posicion) {
        String[] coordinates = posicion.split(",");
        double latitude = Double.parseDouble(coordinates[0]);
        double longitude = Double.parseDouble(coordinates[1]);
        GeometryFactory geometryFactory= new GeometryFactory();
        Point resultado= geometryFactory.createPoint(new Coordinate(longitude, latitude));
        return resultado;

    }

}
