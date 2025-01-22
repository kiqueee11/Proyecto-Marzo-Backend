package com.example.auth_service.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auth_service.model.UserAuthData;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuthData, Long> {


    
}
