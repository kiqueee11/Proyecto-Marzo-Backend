package com.profiles.app.profile_manager_app.validators;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidadorFechaFromatoISO8601 {
        public static boolean esValida(String dateStr) {
        try {
            LocalDateTime.parse(dateStr, DateTimeFormatter.ISO_DATE_TIME);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
