package com.example.auth_service.usecases;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.auth_service.Utils.UIDGenerator;
import com.example.auth_service.feignInterfaces.MediaServiceClient;
import com.example.auth_service.feignInterfaces.SettingsServiceClient;
import com.example.auth_service.feignInterfaces.UserServiceClient;
import com.example.auth_service.model.Autenticacion;
import com.example.auth_service.repository.UserAuthRepository;
import com.example.auth_service.validator.AuthValidator;

@Service
public class SignUpUseCase {
    private final UserAuthRepository userAuthRepository;
    private final UserServiceClient userServiceClient;
    private final SettingsServiceClient settingsServiceClient;
    private final MediaServiceClient mediaServiceClient;
    private final PasswordEncoder passwordEncoder;

    public SignUpUseCase(UserAuthRepository userAuthRepository, UserServiceClient userServiceClient,
            SettingsServiceClient settingsServiceClient, MediaServiceClient mediaServiceClient,
            PasswordEncoder passwordEncoder) {
        this.userAuthRepository = userAuthRepository;
        this.userServiceClient = userServiceClient;
        this.passwordEncoder = passwordEncoder;
        this.settingsServiceClient = settingsServiceClient;
        this.mediaServiceClient = mediaServiceClient;
    }

    public void execute(String name, String password, String email, MultipartFile image1, MultipartFile image2,
            MultipartFile image3, MultipartFile image4, MultipartFile image5, MultipartFile image6, String sexo,
            String descripcion,
            String fechaNacimiento, String posicion) {
        AuthValidator.validarDatosRegistro(name, password, email, userAuthRepository);
        String uidString = UIDGenerator.generateId(15);
        List<MultipartFile> images = new ArrayList<>();
        List<String> urls = new ArrayList<>(6);

        if (image1 != null) {
            images.add(image1);
        }
        if (image2 != null) {
            images.add(image2);
        }
        if (image3 != null) {
            images.add(image3);
        }
        if (image4 != null) {
            images.add(image4);
        }
        if (image5 != null) {
            images.add(image5);
        }
        if (image6 != null) {
            images.add(image6);
        }

        for (int i = 0; i < images.size(); i++) {

            urls.add(mediaServiceClient.uploadMedia(images.get(i), uidString, i + 1));

        }

        userServiceClient.createUser(uidString, name,
                email, urls.get(0), urls.get(1), urls.get(2), urls.get(3), urls.get(4), urls.get(5),
                sexo, descripcion, fechaNacimiento, posicion);
        settingsServiceClient.saveSettingsFromservice(uidString, 18, 30, 50, sexo);

        String encodedPassword = passwordEncoder.encode(password);
        Autenticacion userAuthData = new Autenticacion();
        userAuthData.setEmail(email);
        userAuthData.setName(name);
        userAuthData.setIdUsuario(uidString);
        userAuthData.setClave(encodedPassword);

        userAuthRepository.save(userAuthData);

    }

}
