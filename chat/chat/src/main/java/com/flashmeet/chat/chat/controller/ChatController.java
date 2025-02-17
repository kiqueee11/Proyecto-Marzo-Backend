package com.flashmeet.chat.chat.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flashmeet.chat.chat.service.*;
import com.flashmeet.chat.chat.repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.flashmeet.chat.chat.model.ChatModel;


@RestController
@RequestMapping("/chats")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/create")
    public ChatModel createChat(@RequestParam String user1_id, @RequestParam String user2_id) {
        

        try {
            return chatService.createChat(user1_id, user2_id);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
           throw new RuntimeException(e.getMessage());
        }
    }

}
