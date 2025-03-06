package com.example.auth_service.feignInterfaces;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "settingsservice")
public interface SettingsServiceClient {

    @PostMapping(value = "/settings/internal/saveSettingsFromservice")
    public String saveSettingsFromservice(@RequestParam String userId,
            @RequestParam int maxAge,
            @RequestParam int minAge, @RequestParam int distance, @RequestParam String sexualPreference);

}
