package com.flashmeet.messages.Usecases;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.flashmeet.messages.DTOs.MessageDTO;
import com.flashmeet.messages.exceptions.MessageServiceException;
import com.flashmeet.messages.model.MessageModel;
import com.flashmeet.messages.repository.ChatRepository;
import com.flashmeet.messages.repository.MessagesRepository;
import com.flashmeet.messages.exceptions.*;

@Service
public class SendMessageUseCase {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired

    private MessagesRepository messagesRepository;
    @Autowired
    private ChatRepository chatRepository;

    public void execute(MessageDTO messageDTO, String senderUserId) {
        Map<String, Object> chatData = getChatData(messageDTO.getChatId());
        if (chatData == null) {
            throw new MessageServiceException(ErrCodes.THE_MESSAGE_IS_NOT_LINKED_TO_ANY_CHAT,
                    "Este mensaje no pertence a ningun chat", HttpStatus.NOT_FOUND);
        }

        if (!chatData.get("user1id").equals(senderUserId) && !chatData.get("user2id").equals(senderUserId)) {

            throw new MessageServiceException(ErrCodes.YOU_ARE_NOT_ALLOWED_TO_WRITE_TO_THIS_CHAT,
                    "No tienes permiso para escribir en este chat, no eres ninguno de los participantes",
                    HttpStatus.UNAUTHORIZED);
        }

        MessageModel messageModel = new MessageModel();
        messageModel.setMessageContent(messageDTO.getMessageContent());
        messageModel.setSenderId(messageDTO.getSenderId());
        messageModel.setChatId(messageDTO.getChatId());
        messageModel.setMessageUID(messageDTO.getMessageUID());
        messageModel.setMessageRead(messageDTO.isMessageRead());
        messagesRepository.save(messageModel);
        kafkaTemplate.send("messages", messageDTO.getMessageContent());

    }

    private Map<String, Object> getChatData(String chatId) {
        Optional<Map<String, Object>> chatData = chatRepository.getChatData(chatId);

        if (chatData.isPresent()) {
            return chatData.get();
        } else {
            return null;
        }
    }

}
