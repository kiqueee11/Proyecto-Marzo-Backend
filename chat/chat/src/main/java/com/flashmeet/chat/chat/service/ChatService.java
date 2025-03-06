package com.flashmeet.chat.chat.service;

import org.springframework.stereotype.Service;

import com.flashmeet.chat.chat.model.ChatModel;
import com.flashmeet.chat.chat.repository.ChatRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flashmeet.chat.chat.Usecases.CreateChatUseCase;
import com.flashmeet.chat.chat.Usecases.DeleteChatUseCase;
import com.flashmeet.chat.chat.Usecases.RevealChatIdentityUseCase;
import com.flashmeet.chat.chat.feignClientInterfaces.UserServiceClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatRepository chatRepository;

    public final CreateChatUseCase createChatUseCase;

    public final DeleteChatUseCase deleteChatUseCase;

    public final RevealChatIdentityUseCase revealChatIdentityUseCase;

    public ChatService(ChatRepository chatRepository,
            DeleteChatUseCase deleteChatUseCase, RevealChatIdentityUseCase revealChatIdentityUseCase,
            CreateChatUseCase createChatUseCase) {
        this.chatRepository = chatRepository;
        this.deleteChatUseCase = deleteChatUseCase;
        this.revealChatIdentityUseCase = revealChatIdentityUseCase;
        this.createChatUseCase = createChatUseCase;
    }

}
