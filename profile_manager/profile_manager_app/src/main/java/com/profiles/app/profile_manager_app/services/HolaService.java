package com.profiles.app.profile_manager_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profiles.app.profile_manager_app.models.HolaModel;
import com.profiles.app.profile_manager_app.repository.HolaRepository;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class HolaService {

    private HolaRepository holaRepository;

    private String[] messages = {
            "Hello, world!",
            "Greetings!",
            "Salutations!",
            "Welcome!",
            "Good day!",
            "How are you?",
            "What's up?",
            "Hey there!",
            "Hi!",
            "Aloha!",
            "Bonjour!",
            "Hola!",
            "Ciao!",
            "Guten Tag!",
            "Konnichiwa!",
            "Annyeonghaseyo!",
            "Namaste!",
            "Sawasdee!",
            "Merhaba!",
            "Shalom!",
            "As-salamu alaykum!"
    };

    @Autowired
    public HolaService(HolaRepository holaRepository) {
        this.holaRepository = holaRepository;
    }

    public String getMessages() {
        return holaRepository.findAll().get(0).getMessage();
    }

    public void saveMessage() {
        HolaModel holamodel = new HolaModel();
        holamodel.setMessage(getRandomMessage());

        holaRepository.saveAndFlush(holamodel);
    }

    private String getRandomMessage() {
        int randomIndex = (int) (Math.random() * messages.length);
        return messages[randomIndex];
    }

}
