package com.profiles.app.profile_manager_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.profiles.app.profile_manager_app.models.DatosUsuario;

@Repository
public interface  UserRepository extends JpaRepository<DatosUsuario, Long> {
 
    Optional<DatosUsuario> findByEmail(String email);
    
}
