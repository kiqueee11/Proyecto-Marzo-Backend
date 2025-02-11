package com.profiles.app.profile_manager_app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.profiles.app.profile_manager_app.models.DatosUsuario;
import com.profiles.app.profile_manager_app.services.UserService;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.geo.Point;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/users")
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
    public ResponseEntity<DatosUsuario> createUser(
            @RequestParam String uidString,
            @RequestParam String userName,
            @RequestParam String email, @RequestParam String image1, @RequestParam String image2,
            @RequestParam String image3, @RequestParam String image4, @RequestParam String image5,

            @RequestParam String image6, @RequestParam String sexo, @RequestParam String descripcion,
            @RequestParam String fechaNacimiento,
            @RequestParam String posicion) {

        DatosUsuario user = userService.createUser(uidString, userName, email, image1, image2, image3, image4, image5,
                image6, sexo,
                descripcion, fechaNacimiento, posicion);

        return ResponseEntity.ok(user);
    }

    /**
     * Obtiene un usuario por su correo electrónico.
     * 
     * @param email El correo electrónico del usuario.
     * @return Un ResponseEntity con un Map que contiene el correo electrónico del
     *         usuario o "NOT_FOUND".
     */
    @GetMapping("/internal/get-user")
    public ResponseEntity<Map<String, Object>> getUserByEmail(@RequestParam String email) {
        Optional<DatosUsuario> user = this.userService.findUserByEmail(email);

        if (user.isPresent()) {
            return ResponseEntity.ok(Map.of("email", user.get().getEmail()));
        } else {
            return ResponseEntity.ok(Map.of("email", "NOT_FOUND"));
        }

    }

}
