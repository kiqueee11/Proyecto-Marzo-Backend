package com.flashmeet.settings.usecases;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.flashmeet.settings.model.SettingsModel;
import com.flashmeet.settings.repository.SettingsRepository;

import lombok.Getter;

@Getter
@Component
public class GetSettingsUseCase {

    private SettingsRepository settingsRepository;

    public GetSettingsUseCase(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public SettingsModel execute(String userId) {

        Optional<SettingsModel> settings = settingsRepository.findByUserId(userId);

        if (settings.isPresent()) {

            return settings.get();

        } else {
            return null;
        }

    }
}
