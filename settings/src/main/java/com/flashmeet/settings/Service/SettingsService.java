package com.flashmeet.settings.Service;

import org.springframework.stereotype.Service;

import com.flashmeet.settings.model.SettingsModel;
import com.flashmeet.settings.repository.SettingsRepository;

@Service
public class SettingsService {
    
    private SettingsRepository settingsRepository;

    public SettingsService(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public void saveSettings(SettingsModel settingsModel) {
        settingsRepository.save(settingsModel);
    }

}
