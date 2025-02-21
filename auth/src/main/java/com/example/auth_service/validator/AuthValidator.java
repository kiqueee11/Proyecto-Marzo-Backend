package com.example.auth_service.validator;

import org.apache.commons.validator.routines.*;
import org.springframework.http.HttpStatus;

import com.example.auth_service.exceptions.ErrorCod;
import com.example.auth_service.exceptions.UserAuthException;
import com.example.auth_service.repository.UserAuthRepository;

public class AuthValidator {

    /**
     * Valida los datos de registro de un usuario.
     * Lanza una excepción UserAuthException si alguno de los datos no es válido.
     * 
     * @param name               El nombre del usuario. No puede ser nulo ni estar
     *                           vacío.
     * @param password           La contraseña del usuario. No puede ser nula y debe
     *                           tener al menos 6 caracteres.
     * @param email              El correo electrónico del usuario. No puede ser
     *                           nulo, debe tener un formato válido y no debe estar
     *                           vacío.
     * @param userAuthRepository El repositorio de usuarios. Se utiliza para
     *                           comprobar si ya existe un usuario con el mismo
     *                           correo electrónico.
     * @throws UserAuthException Si alguno de los datos no es válido.
     */

    public static void validarDatosRegistro(String name, String password, String email,
            UserAuthRepository userAuthRepository) throws UserAuthException {
        if (name == null || name.trim().isEmpty()) {
            throw new UserAuthException(ErrorCod.EMPTY_NAME_ERROR,
                    "El nombre no puede estar vacío", HttpStatus.BAD_REQUEST);
        }
        if (password == null || password.length() < 6) {
            throw new UserAuthException(ErrorCod.INVALID_PASSWORD_ERROR,
                    "La contraseña debe tener al menos 6 caracteres", HttpStatus.BAD_REQUEST);
        }
        if (email == null || !EmailValidator.getInstance().isValid(email) || email.trim().isEmpty()) {
            throw new UserAuthException(ErrorCod.INVALID_EMAIL_ERROR,
                    "El email debe tener un formato válido y no puede estar vacío", HttpStatus.BAD_REQUEST);
        }

        if (userAuthRepository.findByEmail(email).isPresent()) {
            throw new UserAuthException(ErrorCod.USER_ALREADY_EXISTS_ERROR,
                    "Ya existe un usuario con el email dado", HttpStatus.CONFLICT);

        }

    }

}
