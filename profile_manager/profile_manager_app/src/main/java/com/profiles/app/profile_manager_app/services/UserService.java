package com.profiles.app.profile_manager_app.services;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.WKTWriter;

import com.profiles.app.profile_manager_app.DTOs.DatosUsuarioDTO;
import com.profiles.app.profile_manager_app.Exceptions.UserException;
import com.profiles.app.profile_manager_app.models.DatosUsuario;
import com.profiles.app.profile_manager_app.repository.UserRepository;
import com.profiles.app.profile_manager_app.usecases.CreateUserUseCase;
import com.profiles.app.profile_manager_app.usecases.GeUserModelUseCase;
import com.profiles.app.profile_manager_app.usecases.GetUserByEmailUseCase;
import com.profiles.app.profile_manager_app.usecases.GetUserByIdUseCase;
import com.profiles.app.profile_manager_app.usecases.GetUserByPositionUsecase;
import com.profiles.app.profile_manager_app.utils.PointParser;

@Service
public class UserService {

    private UserRepository userRepository;
    public CreateUserUseCase createUserUseCase;
    public GetUserByEmailUseCase getUserByEmailUseCase;
    public GetUserByPositionUsecase getUserByPositionUsecase;
    public GetUserByIdUseCase getUserByIdUseCase;
    public GeUserModelUseCase getUserModelUseCase;


    public UserService(UserRepository userRepository, CreateUserUseCase createUserUseCase,
            GetUserByEmailUseCase getUserByEmailUseCase, GetUserByPositionUsecase getUserByPositionUsecase,
            GetUserByIdUseCase getUserByIdUseCase, GeUserModelUseCase getUserModelUseCase) {
        this.userRepository = userRepository;
        this.createUserUseCase = createUserUseCase;
        this.getUserByEmailUseCase = getUserByEmailUseCase;
        this.getUserByPositionUsecase = getUserByPositionUsecase;
        this.getUserByIdUseCase = getUserByIdUseCase;
        this.getUserModelUseCase = getUserModelUseCase;

    }

}
