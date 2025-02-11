package com.example.auth_service.services;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth_service.Utils.UIDGenerator;
import com.example.auth_service.exceptions.UserAuthException;
import com.example.auth_service.interfaces.UserServiceClient;
import com.example.auth_service.model.Autenticacion;
import com.example.auth_service.repository.UserAuthRepository;

import lombok.Data;

@Service
@Data
public class UserAuthServices {

    private UserAuthRepository userAuthRepository;
    private UserServiceClient userServiceClient;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserAuthServices(UserAuthRepository userAuthRepository, UserServiceClient userServiceClient) {
        this.userAuthRepository = userAuthRepository;
        this.userServiceClient = userServiceClient;
    }


    /**
     * Crea los datos de autenticación para un nuevo usuario.
     *
     * Este método primero verifica si ya existe un usuario con el correo electrónico proporcionado en el servicio `profile_manager`.
     * Si el usuario existe, se lanza una excepción `UserAuthException`. Si no, procede a crear un nuevo usuario
     * en `profile_manager` a través de `userServiceClient`. Después de la creación exitosa del usuario en `profile_manager`,
     * crea y guarda una entidad `Autenticacion` correspondiente en la base de datos del servicio `auth`, almacenando
     * el correo electrónico, el nombre y una contraseña codificada con bcrypt del usuario.
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
     * @throws UserAuthException si ya existe un usuario con el correo electrónico dado.
     */

    public Autenticacion creaAuthData(String name, String password, String email, String image1, String image2,
            String image3, String image4, String image5, String image6,String sexo,String descripcion, String fechaNacimiento, String posicion) {

                String uidString=UIDGenerator.generateId(15);
                Optional<ResponseEntity<Map<String, Object>>> userData = userServiceClient.getUserByEmail(email);
                String emailString = userData.get().getBody().get("email").toString();
        if (email.equals(emailString)) {

            throw new UserAuthException("USER_ALREADY_EXISTS:USER_ERROR");

        } else {
             Optional<ResponseEntity<Map<String, Object>>> newUserData = userServiceClient.createUser(uidString,name, password, email, image1, image2,
                    image3, image4, image5, image6,sexo,descripcion,fechaNacimiento,posicion);
            String encodedPassword = passwordEncoder.encode(password);
            Autenticacion userAuthData = new Autenticacion();
            userAuthData.setEmail(email);
            userAuthData.setName(name);
            userAuthData.setIdUsuario(emailString);
            
            userAuthData.setClave(encodedPassword);
            return userAuthRepository.save(userAuthData);
        }

    }





}
