package com.example.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auth_service.model.AuthTokens;
import java.util.List;
import java.util.Optional;

@Repository
public interface TokensRepository extends JpaRepository<AuthTokens, Long> {

    AuthTokens findByTokenUID(String tokenUID);

    Optional<List<AuthTokens>> findAllByUserUID(String userId);

}
