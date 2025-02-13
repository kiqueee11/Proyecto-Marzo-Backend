package com.example.auth_service.validator;

import org.apache.commons.validator.routines.*;

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
        try {
            if (name == null || name.trim().isEmpty()) {
                throw new UserAuthException("ERROR_AUTENTICACION: El nombre no puede estar vacío");
            }
            if (password == null || password.length() < 6) {
                throw new UserAuthException("ERROR_AUTENTICACION: La contraseña debe tener al menos 6 caracteres");
            }
            if (email == null || !EmailValidator.getInstance().isValid(email) || email.trim().isEmpty()) {
                throw new UserAuthException("ERROR_AUTENTICACION: El formato del correo electrónico es incorrecto");
            }

            if (userAuthRepository.findByEmail(email).isPresent()) {
                throw new UserAuthException("ERROR_AUTENTICACION: EL USUARIO CON ESTE EMAIL YA EXISTE");

            }

        } catch (Exception e) {

            if (e.getClass() == UserAuthException.class) {
                throw (UserAuthException) e;
            } else {
                throw new UserAuthException("NO SE PUDO VALIDAR LOS DATOS DE REGISTRO");

            }
        }

    }

}
