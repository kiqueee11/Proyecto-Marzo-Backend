package com.example.auth_service.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth_service.Utils.UIDGenerator;
import com.example.auth_service.interfaces.SettingsServiceClient;
import com.example.auth_service.interfaces.UserServiceClient;
import com.example.auth_service.model.Autenticacion;
import com.example.auth_service.repository.UserAuthRepository;
import com.example.auth_service.validator.AuthValidator;

@Service
public class SignUpUseCase {
    private final UserAuthRepository userAuthRepository;
    private final UserServiceClient userServiceClient;
    private final SettingsServiceClient settingsServiceClient;
    private final PasswordEncoder passwordEncoder;

    public SignUpUseCase(UserAuthRepository userAuthRepository, UserServiceClient userServiceClient,
            SettingsServiceClient settingsServiceClient, PasswordEncoder passwordEncoder) {
        this.userAuthRepository = userAuthRepository;
        this.userServiceClient = userServiceClient;
        this.passwordEncoder = passwordEncoder;
        this.settingsServiceClient = settingsServiceClient;
    }

    public void execute(String name, String password, String email, String image1, String image2,
            String image3, String image4, String image5, String image6, String sexo, String descripcion,
            String fechaNacimiento, String posicion) {
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

    }

}
