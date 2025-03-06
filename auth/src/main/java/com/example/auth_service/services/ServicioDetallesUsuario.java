package com.example.auth_service.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.auth_service.CustomUserDetails;
import com.example.auth_service.model.Autenticacion;
import com.example.auth_service.repository.UserAuthRepository;

@Service
public class ServicioDetallesUsuario implements UserDetailsService {

    UserAuthRepository userAuthRepository;

    public ServicioDetallesUsuario(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Autenticacion> autenticacion = userAuthRepository.findByEmail(username);
        if (autenticacion.isPresent()) {
            UserDetails user = new CustomUserDetails(autenticacion.get().getEmail(),
                    autenticacion.get().getClave(),
                    autenticacion.get().getIdUsuario(),                    
                    List.of(new SimpleGrantedAuthority("USER")));

            return user;
        }

        else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

    }

}
