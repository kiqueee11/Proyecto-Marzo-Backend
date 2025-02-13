package com.example.auth_service.Utils;



/**
 * Clase para generar IDs únicos.
 */
public class UIDGenerator {

    /**
     * Genera un ID único aleatorio con la longitud especificada.
     *
     * @param length La longitud del ID a generar.
     * @return Un ID único aleatorio como una cadena de caracteres.
     */
    public static  String generateId(int length){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();

    }
    
}
