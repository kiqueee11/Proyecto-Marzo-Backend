package com.flashmeet.settings.Service;

import org.springframework.stereotype.Service;

import com.flashmeet.settings.model.SettingsModel;
import com.flashmeet.settings.repository.SettingsRepository;
import com.flashmeet.settings.usecases.GetSettingsUseCase;
import com.flashmeet.settings.usecases.SaveSettingsUseCase;

import lombok.Getter;
@Getter
@Service
public class SettingsService {


    private GetSettingsUseCase getSettingsUseCase;

    private SaveSettingsUseCase saveSettingsUseCase;

    public SettingsService(GetSettingsUseCase getSettingsUseCase, SaveSettingsUseCase saveSettingsUseCase) {
        this.getSettingsUseCase = getSettingsUseCase;
        this.saveSettingsUseCase = saveSettingsUseCase;
    }




  

    


}
