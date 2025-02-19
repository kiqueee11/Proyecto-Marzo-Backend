package com.profiles.app.profile_manager_app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.profiles.app.profile_manager_app.models.DatosUsuario;
import com.profiles.app.profile_manager_app.services.UserService;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.json.Json;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.data.geo.Point;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @GetMapping("/internal/get-user-by-id")
    public ResponseEntity<String> getUserById(@RequestParam String id) {
        Optional<DatosUsuario> user = this.userService.getUserById(id);
        ObjectMapper objectMapper = new ObjectMapper();

        if (user.isPresent()) {
            DatosUsuario usuario = user.get();

            Map<String, Object> userMap = new HashMap<>();
            userMap.put("id", usuario.getId());
            userMap.put("nombre", usuario.getNombre());
            userMap.put("imagen1", usuario.getImagen1());
            userMap.put("imagen2", usuario.getImagen2());
            userMap.put("imagen3", usuario.getImagen3());
            userMap.put("imagen4", usuario.getImagen4());
            userMap.put("imagen5", usuario.getImagen5());
            userMap.put("imagen6", usuario.getImagen6());
            userMap.put("sexo", usuario.getSexo());
            userMap.put("descripcion", usuario.getDescripcion());
            userMap.put("fechaNacimiento", usuario.getFechaNacimiento().toString());
            userMap.put("positionLatitud", usuario.getPosicion().getX());
            userMap.put("positionLongitud", usuario.getPosicion().getY());

            try {
                return ResponseEntity.ok(objectMapper.writeValueAsString(userMap));
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                throw new RuntimeException(e);
            }

        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/buscarusuarioporposicion")
    public ResponseEntity<?> buscarUsuarioPorPosicion(@RequestParam String posicion, @RequestParam int distancia) {

        return userService.buscarUsuarioPorPosicion(posicion, distancia);
    }

}
