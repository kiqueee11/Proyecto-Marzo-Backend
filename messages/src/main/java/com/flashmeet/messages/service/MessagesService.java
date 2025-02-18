package com.flashmeet.messages.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flashmeet.messages.model.MessageModel;
import com.flashmeet.messages.repository.MessagesRepository;
import com.github.andrewoma.dexx.collection.List;

@Service
public class MessagesService {


    private MessagesRepository messagesRepository;

    public MessagesService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }


    @SuppressWarnings("unchecked")
    public List<MessageModel> getAllMessages() {
        return (List<MessageModel>) messagesRepository.findAll();
    }

    public ResponseEntity<MessageModel>saveMessage(MessageModel message){
        return ResponseEntity.ok(messagesRepository.save(message));
    }
    
}
