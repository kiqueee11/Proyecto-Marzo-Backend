package com.example.auth_service.auth.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.rmi.server.UID;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.auth_service.DTOs.AuthDTO;
import com.example.auth_service.Utils.UIDGenerator;
import com.example.auth_service.componentes.JWT.CreadorTokenJWT;
import com.example.auth_service.componentes.JWT.ValidadorJWT;
import com.example.auth_service.exceptions.UserAuthException;
import com.example.auth_service.interfaces.UserServiceClient;
import com.example.auth_service.model.Autenticacion;
import com.example.auth_service.repository.UserAuthRepository;
import com.example.auth_service.services.UserAuthServices;

import jakarta.inject.Inject;

public class UserAuthServiceTest {

    @Mock
    private UserAuthRepository userAuthRepository; // Mock de UserAuthRepository

    @Mock
    private UserServiceClient userServiceClient; // Mock de UserServiceClient

    @InjectMocks
    private UserAuthServices userAuthServices; // El servicio que estamos probando

    @Mock
    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    @Mock
    private CreadorTokenJWT creadorTokenJWT;

    @Mock
    private ValidadorJWT validadorJWT;

    @InjectMocks
    private UIDGenerator uidGenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreaAuthData_WhenUserExists_ShouldThrowUserAuthException() {
        // Datos de entrada
        String name = "JohnDoe";
        String password = "password123";
        String email = "johndoe@example.com";
        String image1 = "image1.jpg";
        String image2 = "image2.jpg";
        String image3 = "image3.jpg";
        String image4 = "image4.jpg";
        String image5 = "image5.jpg";
        String image6 = "image6.jpg";
        String sexo = "Masculino";
        String descripcion = "Descripción de usuario";
        String fechaNacimiento = "1990-01-01";
        String posicion = "Delantero";

        // Simulamos que ya existe un usuario con el correo "johndoe@example.com"
        Autenticacion existingUser = new Autenticacion();
        existingUser.setEmail(email);
        when(userAuthRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));

        // Verificamos que se lanza la excepción UserAuthException cuando ya existe un
        // usuario
        assertThrows(UserAuthException.class, () -> {
            userAuthServices.creaAuthData(name, password, email, image1, image2, image3, image4, image5, image6, sexo,
                    descripcion, fechaNacimiento, posicion);
        });
    }

    @Test
    void testCreaAuthData_WhenUserDoesNotExist_ShouldCreateUser() {
        // Datos de entrada
        String name = "JohnDoe";
        String password = "password123";
        String email = "johndoe@example.com";
        String image1 = "image1.jpg";
        String image2 = "image2.jpg";
        String image3 = "image3.jpg";
        String image4 = "image4.jpg";
        String image5 = "image5.jpg";
        String image6 = "image6.jpg";
        String sexo = "Masculino";
        String descripcion = "Descripción de usuario";
        String fechaNacimiento = "1990-01-01";
        String posicion = "Delantero";

        // Simulamos que NO existe un usuario con el correo "johndoe@example.com"
        when(userAuthRepository.findByEmail(email)).thenReturn(Optional.empty());

        try (MockedStatic<UIDGenerator> mockedStatic = Mockito.mockStatic(UIDGenerator.class)) {
            mockedStatic.when(() -> UIDGenerator.generateId(15)).thenReturn("uidString");
        } catch (Exception e) {
            // TODO: handle exception
        }

        // Simulamos que el método createUser del cliente de servicio funciona
        // correctamente
        when(userServiceClient.createUser("uidString", name, email, image1, image2, image3, image4, image5,
                image6, sexo, descripcion, fechaNacimiento, posicion))
                .thenReturn(null); // No importa lo que devuelva, solo estamos probando que no falle.
        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");

        // Simulamos la creación y guardado del usuario
        Autenticacion newUser = new Autenticacion();
        newUser.setIdUsuario("uidString");
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setClave("encodedPassword");

        // Asegúrate de que el mock del repositorio devuelva el objeto correcto
        when(userAuthRepository.save(any(Autenticacion.class))).thenReturn(newUser); // Verificamos que el servicio crea
                                                                                     // el usuario sin lanzar
                                                                                     // excepciones
        AuthDTO createdUser = userAuthServices.creaAuthData(name, password, email, image1, image2, image3, image4,
                image5, image6, sexo,
                descripcion, fechaNacimiento, posicion);

        // Verificamos que el usuario creado no sea null
        assertNotNull(createdUser);
    }


    @Test
    void testCreaAuthData_WhenNameIsEmpty_ShouldThrowUserAuthException() {
        // Datos de entrada
        String name = "";
        String password = "password123";
        String email = "johndoe@example.com";
        String image1 = "image1.jpg";
        String image2 = "image2.jpg";
        String image3 = "image3.jpg";
        String image4 = "image4.jpg";
        String image5 = "image5.jpg";
        String image6 = "image6.jpg";
        String sexo = "Masculino";
        String descripcion = "Descripción de usuario";
        String fechaNacimiento = "1990-01-01";
        String posicion = "Delantero";

        // Verificamos que se lanza la excepción UserAuthException cuando el nombre está vacío
       UserAuthException exception = assertThrows(UserAuthException.class, () -> {
            userAuthServices.creaAuthData(name, password, email, image1, image2, image3, image4, image5, image6, sexo,
                    descripcion, fechaNacimiento, posicion);
        });

        assertEquals("ERROR_AUTENTICACION: El nombre no puede estar vacío", exception.getMessage());
    }


    @Test
    void testCreaAuthData_WhenPasswordIsLessThan6Characters_ShouldThrowUserAuthException() {
        // Datos de entrada
        String name = "JohnDoe";
        String password = "pass";
        String email = "johndoe@example.com";
        String image1 = "image1.jpg";
        String image2 = "image2.jpg";
        String image3 = "image3.jpg";
        String image4 = "image4.jpg";
        String image5 = "image5.jpg";
        String image6 = "image6.jpg";
        String sexo = "Masculino";
        String descripcion = "Descripción de usuario";
        String fechaNacimiento = "1990-01-01";
        String posicion = "Delantero";

        // Verificamos que se lanza la excepción UserAuthException cuando la contraseña tiene menos de 6 caracteres
        UserAuthException exception = assertThrows(UserAuthException.class, () -> {
            userAuthServices.creaAuthData(name, password, email, image1, image2, image3, image4, image5, image6, sexo,
                    descripcion, fechaNacimiento, posicion);
        });

        assertEquals("ERROR_AUTENTICACION: La contraseña debe tener al menos 6 caracteres", exception.getMessage());
    }

    @Test
    void testCreaAuthData_EmailMalFormateado() {
        // Datos de entrada
        String name = "JohnDoe";
        String password = "passworddd";
        String email = "johndoeexample.com";
        String image1 = "image1.jpg";
        String image2 = "image2.jpg";
        String image3 = "image3.jpg";
        String image4 = "image4.jpg";
        String image5 = "image5.jpg";
        String image6 = "image6.jpg";
        String sexo = "Masculino";
        String descripcion = "Descripción de usuario";
        String fechaNacimiento = "1990-01-01";
        String posicion = "Delantero";

        // Verificamos que se lanza la excepción UserAuthException cuando la contraseña tiene menos de 6 caracteres
        UserAuthException exception = assertThrows(UserAuthException.class, () -> {
            userAuthServices.creaAuthData(name, password, email, image1, image2, image3, image4, image5, image6, sexo,
                    descripcion, fechaNacimiento, posicion);
        });

        assertEquals("ERROR_AUTENTICACION: El formato del correo electrónico es incorrecto", exception.getMessage());
    }


}
