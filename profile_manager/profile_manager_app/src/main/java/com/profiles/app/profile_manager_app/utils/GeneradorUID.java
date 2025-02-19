package com.profiles.app.profile_manager_app.utils;

public class GeneradorUID {

     /**
     * Generates an unique id with letters and numbers
     * 
     * @param length the length of the UID
     * @return An String UID
     */
    public static String generateId(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();

    }
    
}
