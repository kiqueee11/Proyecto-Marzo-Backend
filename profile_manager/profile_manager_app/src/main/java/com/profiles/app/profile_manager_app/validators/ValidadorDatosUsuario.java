package com.profiles.app.profile_manager_app.validators;

import org.apache.commons.validator.routines.*;

import com.profiles.app.profile_manager_app.Exceptions.UserException;
import com.profiles.app.profile_manager_app.repository.UserRepository;

public class ValidadorDatosUsuario {

    /**
     * Valida los datos de un usuario.
     *
     * @param nombre          El nombre del usuario.
     * @param idUsuario       El ID del usuario.
     * @param email           El correo electrónico del usuario.
     * @param imagen1         La primera imagen del usuario.
     * @param imagen2         La segunda imagen del usuario.
     * @param imagen3         La tercera imagen del usuario.
     * @param imagen4         La cuarta imagen del usuario.
     * @param imagen5         La quinta imagen del usuario.
     * @param imagen6         La sexta imagen del usuario.
     * @param sexo            El sexo del usuario.
     * @param descripcion     La descripción del usuario.
     * @param fechaNacimiento La fecha de nacimiento del usuario.
     * @param posicion        La posición del usuario.
     * @throws UserException Si alguno de los datos no es válido.
     */
    public static void validarDatosUsuario(String nombre, String idUsuario, String email, String imagen1,
            String imagen2, String imagen3, String imagen4, String imagen5, String imagen6, String sexo,
            String descripcion, String fechaNacimiento, String posicion, UserRepository userRepository) {
        if (nombre == null || nombre.trim().isEmpty() || nombre.length() > 50) {
            throw new UserException("ERROR: EL NOMBRE DEBE TENER ENTRE 1 Y 50 CARACTERES");
        }

        if (idUsuario == null || idUsuario.trim().isEmpty() || idUsuario.length() > 50) {
            throw new UserException("ERROR: EL ID DE USUARIO DEBE TENER ENTRE 1 Y 50 CARACTERES");
        }
        if (email == null || !EmailValidator.getInstance().isValid(email) || email.trim().isEmpty()
                || email.length() > 50) {
            throw new UserException(
                    "ERROR_AUTENTICACION: El formato del correo electrónico es incorrecto, asegurate de que el formato sea 'correo@dominio' y que tenga menos de 50 caracteres ");
        }

        if (imagen1 == null || imagen1.trim().isEmpty() || imagen1.length() > 255) {
            throw new UserException("ERROR: LA IMAGEN 1 DEBE TENER ENTRE 1 Y 255 CARACTERES");
        }
        if (imagen2 == null || imagen2.trim().isEmpty() || imagen2.length() > 255) {
            throw new UserException("ERROR: LA IMAGEN 2 DEBE TENER ENTRE 1 Y 255 CARACTERES");
        }
        if (imagen3 == null || imagen3.trim().isEmpty() || imagen3.length() > 255) {
            throw new UserException("ERROR: LA IMAGEN 3 DEBE TENER ENTRE 1 Y 255 CARACTERES");
        }
        if (imagen4 == null || imagen4.trim().isEmpty() || imagen4.length() > 255) {
            throw new UserException("ERROR: LA IMAGEN 4 DEBE TENER ENTRE 1 Y 255 CARACTERES");
        }
        if (imagen5 == null || imagen5.trim().isEmpty() || imagen5.length() > 255) {
            throw new UserException("ERROR: LA IMAGEN 5 DEBE TENER ENTRE 1 Y 255 CARACTERES");
        }
        if (imagen6 == null || imagen6.trim().isEmpty() || imagen6.length() > 255) {
            throw new UserException("ERROR: LA IMAGEN 6 DEBE TENER ENTRE 1 Y 255 CARACTERES");
        }
        if (sexo == null || sexo.trim().isEmpty() || (!sexo.equals("HOMBRE") && !sexo.equals("MUJER"))) {
            throw new UserException("ERROR: EL SEXO DEBE SER HOMBRE O MUJER");
        }
        if (descripcion == null || descripcion.trim().isEmpty() || descripcion.length() > 250) {
            throw new UserException("ERROR: LA DESCRIPCION DEBE TENER ENTRE 1 Y 250 CARACTERES");
        }
        if (fechaNacimiento == null || fechaNacimiento.trim().isEmpty()
                || !ValidadorFechaFromatoISO8601.esValida(fechaNacimiento)) {
            throw new UserException("ERROR: LA FECHA DE NACIMIENTO NO PUEDE ESTAR VACIA");
        }

        if (posicion == null || posicion.trim().isEmpty() || !coordenadasValidas(posicion)) {
            throw new UserException("ERROR: LA POSICION NO PUEDE ESTAR VACIA O NO TIENE UN FORMATO CORRECTO");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserException("ERROR: EL USUARIO CON ESTE EMAIL YA EXISTE");
        }

    }

    private static boolean coordenadasValidas(String posicion) {
        try {
            String[] parts = posicion.split(",");
            if (parts.length != 2) {
                return false;
            }

            double latitude = Double.parseDouble(parts[0].trim());
            double longitude = Double.parseDouble(parts[1].trim());

            return (latitude >= -90 && latitude <= 90) && (longitude >= -180 && longitude <= 180);
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
