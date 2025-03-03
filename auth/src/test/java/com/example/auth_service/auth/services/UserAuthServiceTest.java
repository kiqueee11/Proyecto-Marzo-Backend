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

}