package com.flashmeet.chat.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flashmeet.chat.chat.service.*;
import com.flashmeet.chat.chat.repository.*;
import com.flashmeet.chat.chat.response.ChatResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.flashmeet.chat.chat.exceptions.ChatException;
import com.flashmeet.chat.chat.exceptions.ErrCodes;
import com.flashmeet.chat.chat.model.ChatModel;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/chats")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/create")
    public ChatResponse<ChatModel> createChat(@RequestParam String user1_id,
            @RequestParam String user2_id) throws JsonMappingException, JsonProcessingException {
        chatService.createChatUseCase.execute(user1_id, user2_id);
        return ChatResponse.success("Exito", null, HttpStatus.OK);

    }

    @PostMapping("/reveal-identity")
    public ChatResponse<ChatModel> revealChatIdentity(@RequestParam String chatId) {

        chatService.revealChatIdentityUseCase.execute(chatId);
        return ChatResponse.success("Exito", null, HttpStatus.OK);

    }

    @PostMapping("/delete-chat")
    public ChatResponse<ChatModel> deleteChat(@RequestParam String chatId) {

        chatService.deleteChatUseCase.execute(chatId);
        return ChatResponse.success("Exito", null, HttpStatus.OK);

    }

}
