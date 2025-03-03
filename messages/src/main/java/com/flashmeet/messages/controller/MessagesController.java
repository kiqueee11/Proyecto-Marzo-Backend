package com.flashmeet.messages.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flashmeet.messages.DTOs.MessageDTO;
import com.flashmeet.messages.model.MessageModel;
import com.flashmeet.messages.response.MessageServiceResponse;
import com.flashmeet.messages.service.MessagesService;

import jakarta.validation.Valid;

import java.security.MessageDigest;
import java.util.List;

import org.apache.kafka.shaded.com.google.protobuf.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<MessageServiceResponse<MessageDTO>> send(@Valid @RequestBody MessageDTO entity, @RequestParam  String userId) {
        
        System.out.println("ESTE ES EL USER ID: "+userId);
        MessageServiceResponse<MessageDTO> messageServiceResponse = MessageServiceResponse.success(
                "SUCCCESS", null,
                HttpStatus.OK);

        messagesService.getSendMessageUseCase().execute(entity, userId);

        return ResponseEntity.ok(messageServiceResponse);
    }

    @PostMapping("/getAllMessages")
    public ResponseEntity<MessageServiceResponse<List<MessageDTO>>> getAllMessages(String chatId) {

        List<MessageDTO> messages = messagesService.getGetAllMessagesUseCase().execute(chatId);

        MessageServiceResponse<List<MessageDTO>> messageServiceResponse = MessageServiceResponse.success(
                "SUCCCESS", messages,
                HttpStatus.OK);

        return ResponseEntity.ok().body(messageServiceResponse);
    }

}
