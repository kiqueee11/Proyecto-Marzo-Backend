package com.flashmeet.messages.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flashmeet.messages.model.MessageModel;
import com.flashmeet.messages.service.MessagesService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    private MessagesService messagesService;

    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @PostMapping("/send")
    public ResponseEntity<MessageModel> send(@RequestBody MessageModel entity) {

        return messagesService.saveMessage(entity);
    }

}
