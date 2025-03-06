package com.flashmeet.settings.Controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flashmeet.settings.Service.SettingsService;
import com.flashmeet.settings.model.SettingsModel;
import com.flashmeet.settings.rersponse.SettingsResponse;

@RestController
@RequestMapping("/settings")
public class SettingsController {

    private SettingsService settingsService;

    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @PostMapping("/save-settings")
    public ResponseEntity<?> saveSettings(@RequestParam String userId,
            @RequestParam int maxAge,
            @RequestParam int minAge, @RequestParam int distance, @RequestParam String sexualPreference) {

        settingsService.getSaveSettingsUseCase().execute(userId, minAge, maxAge, distance,
                sexualPreference);

        SettingsResponse<?> settingsResponse = SettingsResponse.success("SUCCESS", null, HttpStatus.OK);
        return ResponseEntity.status(settingsResponse.getStatusCode()).body(settingsResponse);

    }

    @PostMapping("/internal/saveSettingsFromservice")
    public String saveSettingsFromservice(@RequestParam String userId,
            @RequestParam int maxAge,
            @RequestParam int minAge, @RequestParam int distance, @RequestParam String sexualPreference) {

        settingsService.getSaveSettingsUseCase().execute(userId, minAge, maxAge, distance,
                sexualPreference);

        SettingsResponse<?> settingsResponse = SettingsResponse.success("SUCCESS", null, HttpStatus.OK);
        return "OK";

    }

    @PostMapping("/get-settings")
    public ResponseEntity<SettingsResponse<SettingsModel>> getSettings(@RequestParam("userId") String userId) {

        SettingsModel settings = settingsService.getGetSettingsUseCase().execute(userId);

        SettingsResponse<SettingsModel> settingsResponse = SettingsResponse.success("SUCCESS", settings, HttpStatus.OK);
        return ResponseEntity.status(settingsResponse.getStatusCode()).body(settingsResponse);

    }

}