package com.flashmeet.chat.chat.service;

import org.springframework.stereotype.Service;

import com.flashmeet.chat.chat.model.ChatModel;
import com.flashmeet.chat.chat.repository.ChatRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final UserServiceClient userServiceClient;
    private final ObjectMapper objectMapper = new ObjectMapper();



    public ChatService(ChatRepository chatRepository, UserServiceClient userServiceClient) {
        this.chatRepository = chatRepository;
        this.userServiceClient = userServiceClient;
    }

    public List<ChatModel> getAllChats() {
        return chatRepository.findAll();
    }

    public Optional<ChatModel> getChatById(Long id) {
        return chatRepository.findById(id);
    }

    public ChatModel createChat(String userId1, String userId2) throws JsonMappingException, JsonProcessingException {

       ResponseEntity<String> user1Response = userServiceClient.getUserById(userId1).get();
       ResponseEntity<String>  user2Response = userServiceClient.getUserById(userId2).get();

       if(user1Response.getStatusCode().is2xxSuccessful() && user2Response.getStatusCode().is2xxSuccessful()){
        Map<String, Object> user1 = objectMapper.readValue(user1Response.getBody(), new TypeReference<Map<String, Object>>() {});
        Map<String, Object> user2 = objectMapper.readValue(user2Response.getBody(), new TypeReference<Map<String, Object>>() {});
 
 
         ChatModel chat = new ChatModel();
         chat.setUser1_id(userId1);
         chat.setUser2_id(userId2);
         chat.setUser1_name((String) user1.get("nombre"));
         chat.setUser2_name((String) user2.get("nombre"));
         chat.setUser1_picture((String) user1.get("imagen1"));
         chat.setUser2_picture((String) user2.get("imagen1"));
         chat.setUser1_online(true);
         chat.setUser2_online(true);
         chat.setChatAnnonimous(true);
         
         return chatRepository.save(chat);

       }

       else{
        return null;
       }








    }

    public ChatModel updateChat(Long id, ChatModel chat) {

        Optional<ChatModel> existingChat = chatRepository.findById(id);

        if (existingChat.isPresent()) {
            return chatRepository.save(chat);

        } else {
            return null;
        }

    }

    public void deleteChat(Long id) {
        chatRepository.deleteById(id);
    }

}
