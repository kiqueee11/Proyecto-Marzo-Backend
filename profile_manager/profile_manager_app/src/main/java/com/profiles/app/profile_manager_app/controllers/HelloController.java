package com.profiles.app.profile_manager_app.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.profiles.app.profile_manager_app.services.HolaService;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@NoArgsConstructor
@RequestMapping("/api/users")
public class HelloController {

    private HolaService holaService;

    @Autowired
    public HelloController(HolaService holaService) {
        this.holaService = holaService;
    }


    @GetMapping("/hola")
    public String hello(@RequestParam String param) {
        return "Caminemos pisando las sendas\n" + //
                        "De nuestra inmensa felicidad.\n" + //
                        "En fraternidad, sin separación,\n" + //
                        "¡Cantemos libertad!\n" + //
                        "Tras dos siglos de estar sometidos\n" + //
                        "por la dominación colonial,\n" + //
                        "en fraterna unión, sin discriminar,\n" + //
                        "¡cantemos libertad!\n" + //
                        "¡Gritamos viva, libre Guinea!,\n" + //
                        "y defendamos nuestra Libertad.\n" + //
                        "Cantemos siempre, libre Guinea,\n" + //
                        "y conservemos siempre la unidad.\n" + //
                        "¡Gritemos viva, libre Guinea!,\n" + //
                        "y defendamos nuestra Libertad.\n" + //
                        "Cantemos siempre, libre Guinea.\n" + //
                        "Y conservemos, y conservemos\n" + //
                        "la independencia nacional.\n" + //
                        "y conservemos, y conservemos\n" + //
                        "la independencia nacional." + param;
   
    }

    @GetMapping("/getMessages")
    public String getMessages() {
        return holaService.getMessages();
    }

    @GetMapping("/saveMessage")
    public void saveMessage() {
        holaService.saveMessage();
    }
    
 
    
}
