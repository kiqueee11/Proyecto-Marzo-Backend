package com.profiles.app.profile_manager_app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.Data;
@Data
@Service
public class LanguagesServices {

    /**
     * A list of supported languages for the application.
     * This list includes:
     * - en
     * - es
     * - fr
     * - de
     * - pt
     * - ja
     * - ko
     * - zh
     * - it
     * - ru
     */
    private final List<String> supportedLanguages = Arrays.asList(
            "en", "es", "fr", "de", "pt",
            "ja", "ko", "zh", "it", "ru");

    /**
     * Checks if all the given languages are supported.
     *
     * @param languages a list of language codes to check for support.
     * @return true if all the languages in the list are supported, false otherwise.
     */
    public boolean areLanguagesSupported(List<String> languages) {

        return supportedLanguages.containsAll(languages);

    }

}
