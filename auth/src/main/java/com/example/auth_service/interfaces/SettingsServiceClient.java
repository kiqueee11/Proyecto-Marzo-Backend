package com.example.auth_service.interfaces;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "settings")
public interface SettingsServiceClient {

    @PostMapping(value = "/settings/internal/createSettingsData")
    Optional<String> createSettingsData( @RequestParam String id_usuario, @RequestParam int edad_maxima,@RequestParam int edad_minima,@RequestParam int distancia,
    @RequestParam  String preferencia_sexual);

}
