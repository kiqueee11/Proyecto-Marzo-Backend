package com.example.auth_service.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auth_service.model.Autenticacion;

@Repository
public interface UserAuthRepository extends JpaRepository<Autenticacion, Long> {


    Optional<Autenticacion> findByEmail(String email);

    

    



    
}
