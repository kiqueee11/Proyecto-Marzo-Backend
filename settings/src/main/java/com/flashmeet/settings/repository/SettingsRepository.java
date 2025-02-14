package com.flashmeet.settings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flashmeet.settings.model.SettingsModel;

@Repository
public interface  SettingsRepository extends JpaRepository<SettingsModel, Long> {
   
}