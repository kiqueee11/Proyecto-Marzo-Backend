package com.flashmeet.settings.Controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/internal/createSettingsData")
    public String createSettingsData(@RequestParam String id_usuario,
            @RequestParam int edad_maxima,
            @RequestParam int edad_minima, @RequestParam int distancia, @RequestParam String preferencia_sexual) {

        SettingsModel settingsModel = new SettingsModel();

        settingsModel.setId_usuario(id_usuario);
        settingsModel.setEdad_minima(edad_minima);
        settingsModel.setEdad_maxima(edad_maxima);
        settingsModel.setDistancia(distancia);
        settingsModel.setPreferencia_sexual(preferencia_sexual);
        settingsService.saveSettings(settingsModel);

        return "done";
    }

}