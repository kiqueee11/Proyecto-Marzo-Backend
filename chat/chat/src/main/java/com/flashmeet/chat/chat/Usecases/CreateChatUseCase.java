package com.flashmeet.chat.chat.Usecases;

import java.rmi.server.UID;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flashmeet.chat.chat.exceptions.ChatException;
import com.flashmeet.chat.chat.feignClientInterfaces.UserServiceClient;
import com.flashmeet.chat.chat.model.ChatModel;
import com.flashmeet.chat.chat.repository.ChatRepository;
import com.flashmeet.chat.chat.utils.UIDGenerator;
import com.flashmeet.chat.chat.exceptions.ErrCodes;

@Service

public class CreateChatUseCase {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserServiceClient userServiceClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ChatModel execute(String userId1, String userId2) throws JsonMappingException, JsonProcessingException {

        Optional<ResponseEntity<String>> user1Response = userServiceClient.getUserById(userId1);
        Optional<ResponseEntity<String>> user2Response = userServiceClient.getUserById(userId2);

        if (!user1Response.isPresent() || !user2Response.isPresent()) {
            throw new ChatException(ErrCodes.USER_NOT_FOUND, "El usuario solicitado no existe", HttpStatus.NOT_FOUND);

        }

        Map<String, Object> user1 = objectMapper.readValue(user1Response.get().getBody(),
                new TypeReference<Map<String, Object>>() {
                });
        Map<String, Object> user2 = objectMapper.readValue(user2Response.get().getBody(),
                new TypeReference<Map<String, Object>>() {
                });
  


        ChatModel chat = new ChatModel();
        chat.setUser1Id(userId1);
        chat.setUser2Id(userId2);
        chat.setUser1Name((String) user1.get("nombre"));
        chat.setUser2Name((String) user2.get("nombre"));
        chat.setUser1Picture(((List<String>) user1.get("imagenes")).getFirst());
        chat.setUser2Picture(((List<String>)  user2.get("imagenes")).getFirst());
        chat.setUser1Online(true);
        chat.setUser2Online(true);
        chat.setChatAnnonimous(true);
        chat.setChatId(UIDGenerator.generateId(20));

        return chatRepository.save(chat);

    }

}
