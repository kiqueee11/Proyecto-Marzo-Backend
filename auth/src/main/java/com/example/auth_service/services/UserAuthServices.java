package com.example.auth_service.services;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth_service.CustomUserDetails;
import com.example.auth_service.DTOs.AuthDTO;
import com.example.auth_service.DTOs.LoginDto;
import com.example.auth_service.Utils.UIDGenerator;
import com.example.auth_service.componentes.JWT.CreadorTokenJWT;
import com.example.auth_service.componentes.JWT.ValidadorJWT;
import com.example.auth_service.exceptions.UserAuthException;
import com.example.auth_service.interfaces.SettingsServiceClient;
import com.example.auth_service.interfaces.UserServiceClient;
import com.example.auth_service.model.Autenticacion;
import com.example.auth_service.repository.UserAuthRepository;
import com.example.auth_service.validator.AuthValidator;

import lombok.Data;

@Service
@Data
public class UserAuthServices {

    private UserAuthRepository userAuthRepository;
    private UserServiceClient userServiceClient;

    private SettingsServiceClient settingsServiceClient;

    private AuthenticationManager authenticationManager;

    @Autowired
    private CreadorTokenJWT creadorTokenJWT;

    @Autowired
    private ValidadorJWT validadorJWT;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserAuthServices(UserAuthRepository userAuthRepository, UserServiceClient userServiceClient,
            AuthenticationManager authenticationManager, SettingsServiceClient settingsServiceClient) {
        this.userAuthRepository = userAuthRepository;
        this.userServiceClient = userServiceClient;
        this.authenticationManager = authenticationManager;
        this.settingsServiceClient = settingsServiceClient;
    }

    /**
     * Crea los datos de autenticación para un nuevo usuario.
     *
     * Este método primero verifica si ya existe un usuario con el correo
     * electrónico proporcionado en el servicio `profile_manager`.
     * Si el usuario existe, se lanza una excepción `UserAuthException`. Si no,
     * procede a crear un nuevo usuario
     * en `profile_manager` a través de `userServiceClient`. Después de la creación
     * exitosa del usuario en `profile_manager`,
     * crea y guarda una entidad `Autenticacion` correspondiente en la base de datos
     * del servicio `auth`, almacenando
     * el correo electrónico, el nombre y una contraseña codificada con bcrypt del
     * usuario.
     *
     * @param name     El nombre del usuario.
     * @param password La contraseña del usuario.
     * @param email    El correo electrónico del usuario.
     * @param image1   La primera imagen del usuario.
     * @param image2   La segunda imagen del usuario.
     * @param image3   La tercera imagen del usuario.
     * @param image4   La cuarta imagen del usuario.
     * @param image5   La quinta imagen del usuario.
     * @param image6   La sexta imagen del usuario.
     * @return La entidad `Autenticacion` creada.
     * @throws UserAuthException si ya existe un usuario con el correo electrónico
     *                           dado.
     */

    public AuthDTO creaAuthData(String name, String password, String email, String image1, String image2,
            String image3, String image4, String image5, String image6, String sexo, String descripcion,
            String fechaNacimiento, String posicion) {
        // Validamos los datos necesarios para crear el objeto autenticacion
        AuthValidator.validarDatosRegistro(name, password, email, userAuthRepository);
        String uidString = UIDGenerator.generateId(15);
        userServiceClient.createUser(uidString, name,
                email, image1, image2,
                image3, image4, image5, image6, sexo, descripcion, fechaNacimiento, posicion);
        settingsServiceClient.createSettingsData(uidString, 18, 30, 50, sexo);

        String encodedPassword = passwordEncoder.encode(password);
        Autenticacion userAuthData = new Autenticacion();
        userAuthData.setEmail(email);
        userAuthData.setName(name);
        userAuthData.setIdUsuario(uidString);
        userAuthData.setClave(encodedPassword);

        userAuthRepository.save(userAuthData);

        return new AuthDTO(userAuthData.getName(), userAuthData.getIdUsuario(), null);

    }

    /**
     * Inicia sesión a un usuario dado su correo electrónico y contraseña.
     *
     * @param email    El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     * @return Un token JWT si la autenticación es exitosa, null en caso contrario.
     */

    public LoginDto iniciarSesion(String email, String password) {

        Authentication autenticacion = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));

        CustomUserDetails userDetails = (CustomUserDetails) autenticacion.getPrincipal();
        String token = creadorTokenJWT.generarToken(autenticacion, autenticacion.getAuthorities().stream()
                .findFirst().get().getAuthority());
        return new LoginDto(userDetails.getUsername(), token);

    }

}
