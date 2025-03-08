package com.profiles.app.profile_manager_app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.profiles.app.profile_manager_app.DTOs.DatosUsuarioDTO;
import com.profiles.app.profile_manager_app.Exceptions.ErrCodes;
import com.profiles.app.profile_manager_app.models.DatosUsuario;
import com.profiles.app.profile_manager_app.response.UserServiceResponse;
import com.profiles.app.profile_manager_app.services.UserService;
import com.profiles.app.profile_manager_app.validators.annotations.DateDataValidatorAnnotation;
import com.profiles.app.profile_manager_app.validators.annotations.PositionDataValidatorAnnotation;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.json.Json;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.geo.Point;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
@PropertySource("classpath:validation_error_messages.properties")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Crea un nuevo usuario.
     * 
     * @param uidString El ID del usuario.
     * @param userName  El nombre del usuario.
     * @param password  La contraseña del usuario.
     * @param email     El correo electrónico del usuario.
     */
    @PostMapping("/internal/create-user")
    public ResponseEntity<UserServiceResponse<DatosUsuarioDTO>> createUser(
            @NotBlank @Size(max = 50) @RequestParam String uidString,
            @NotBlank @Size(max = 50) @RequestParam String userName,
            @NotBlank @Email @Size(max = 50) @RequestParam String email,
            @NotBlank @Size(max = 255) @RequestParam String image1,
            @NotBlank @Size(max = 255) @RequestParam String image2,
            @NotBlank @Size(max = 255) @RequestParam String image3,
            @NotBlank @Size(max = 255) @RequestParam String image4,
            @NotBlank @Size(max = 255) @RequestParam String image5,
            @NotBlank @Size(max = 255) @RequestParam String image6,
            @NotBlank @Pattern(regexp = "HOMBRE|MUJER") @RequestParam String sexo,
            @NotBlank @Size(max = 250) @RequestParam String descripcion,
            @NotBlank @DateDataValidatorAnnotation @RequestParam String fechaNacimiento,
            @NotBlank @PositionDataValidatorAnnotation @RequestParam String posicion) {

        userService.createUserUseCase.execute(uidString, userName, email, image1, image2, image3, image4, image5,
                image6, sexo,
                descripcion, fechaNacimiento, posicion);
        UserServiceResponse<DatosUsuarioDTO> response = UserServiceResponse.success("Exito al crear usuario", null,
                HttpStatus.OK);
        return ResponseEntity.ok(response);

    }

    /**
     * Obtiene un usuario por su correo electrónico.
     * 
     * @param email El correo electrónico del usuario.
     * @return Un ResponseEntity con un Map que contiene el correo electrónico del
     *         usuario o "NOT_FOUND".
     */
    @GetMapping("/internal/get-user-by-email")
    public ResponseEntity<UserServiceResponse<DatosUsuarioDTO>> getUserByEmail(@RequestParam String email) {
        DatosUsuarioDTO user = this.userService.getUserByEmailUseCase.execute(email);
        UserServiceResponse<DatosUsuarioDTO> response;

        if (user == null) {
            response = UserServiceResponse.failure("USER_NOT_FOUND", null, HttpStatus.NOT_FOUND,
                    ErrCodes.USER_NOT_FOUND);

            return ResponseEntity.status(response.getStatusCode()).body(response);
        } else {
            response = UserServiceResponse.success("SUCCESS", null, HttpStatus.OK);
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }

    }

    @GetMapping("/internal/get-user-by-id")
    public ResponseEntity<String> getUserById(@RequestParam String id) throws JsonProcessingException {
        DatosUsuarioDTO user = this.userService.getUserByIdUseCase.execute(id);
        UserServiceResponse<DatosUsuarioDTO> response;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        if (user == null) {
            response = UserServiceResponse.failure("USER_NOT_FOUND", null, HttpStatus.NOT_FOUND,
                    ErrCodes.USER_NOT_FOUND);
            String value = mapper.writeValueAsString(response);

            return ResponseEntity.status(response.getStatusCode()).body(value);
        } else {

            response = UserServiceResponse.success("SUCCESS", user, HttpStatus.OK);
            String value = mapper.writeValueAsString(user);

            return ResponseEntity.status(response.getStatusCode()).body(value);
        }
    }

    @PostMapping("/buscarusuarioporposicion")
    public ResponseEntity<UserServiceResponse<List<DatosUsuarioDTO>>> buscarUsuarioPorPosicion(
            @RequestParam String posicion, @RequestParam int distancia) {
        UserServiceResponse<List<DatosUsuarioDTO>> response;

        List<DatosUsuarioDTO> users = this.userService.getUserByPositionUsecase.execute(posicion, distancia);

        if (users == null) {
            response = UserServiceResponse.failure("ERROR", null, HttpStatus.NOT_FOUND,
                    ErrCodes.COULD_NOT_FIND_USERS_NEAR_YOU);

            return ResponseEntity.status(response.getStatusCode()).body(response);
        } else {
            response = UserServiceResponse.success("SUCCESS", users, HttpStatus.OK);
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }

    }


    @PostMapping("/get-user-data")
    public ResponseEntity<UserServiceResponse<DatosUsuario>> getUserDataModelById(@RequestParam("userId") String userId) {
        UserServiceResponse<DatosUsuario> response;

        DatosUsuario users = this.userService.getUserModelUseCase.execute(userId);

        if (users == null) {
            response = UserServiceResponse.failure("ERROR", null, HttpStatus.NOT_FOUND,
                    ErrCodes.COULD_NOT_FIND_USERS_NEAR_YOU);

            return ResponseEntity.status(response.getStatusCode()).body(response);
        } else {
            response = UserServiceResponse.success("SUCCESS", users, HttpStatus.OK);
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }

    }

}
