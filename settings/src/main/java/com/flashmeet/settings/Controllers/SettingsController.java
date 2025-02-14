package com.flashmeet.settings.Controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flashmeet.settings.Service.SettingsService;
import com.flashmeet.settings.model.SettingsModel;

@RestController
@RequestMapping("/settings")
public class SettingsController {

    private SettingsService settingsService;

    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @PostMapping("/save")
    public ResponseEntity<SettingsModel> saveSettings(@RequestBody SettingsModel settingsModel) {
        
        settingsService.saveSettings(settingsModel);
        return ResponseEntity.ok(settingsModel);
    }

}