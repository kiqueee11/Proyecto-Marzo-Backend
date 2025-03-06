package com.flashmeet.settings.usecases;

import org.springframework.stereotype.Component;

import com.flashmeet.settings.model.SettingsModel;
import com.flashmeet.settings.repository.SettingsRepository;

import lombok.Getter;

@Getter
@Component
public class SaveSettingsUseCase {

    private SettingsRepository settingsRepository;

    public SaveSettingsUseCase(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public void execute(String id_usuario,
            int edad_maxima,
            int edad_minima, int distancia, String preferencia_sexual) {

        SettingsModel settingsModel = new SettingsModel();
        settingsModel.setUserId(id_usuario);
        settingsModel.setMaxAge(edad_maxima);
        settingsModel.setMinAge(edad_minima);
        settingsModel.setDistance(distancia);
        settingsModel.setSexualPreference(preferencia_sexual);
        settingsRepository.save(settingsModel);

    }

}
