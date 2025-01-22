package com.profiles.app.profile_manager_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.profiles.app.profile_manager_app.models.HolaModel;

@Repository
public interface HolaRepository extends JpaRepository<HolaModel, Long> {
    
}
