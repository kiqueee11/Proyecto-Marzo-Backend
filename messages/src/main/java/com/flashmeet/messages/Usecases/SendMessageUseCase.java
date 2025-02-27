package com.flashmeet.messages.Usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.flashmeet.messages.DTOs.MessageDTO;
import com.flashmeet.messages.model.MessageModel;
import com.flashmeet.messages.repository.MessagesRepository;

@Service
public class SendMessageUseCase {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired

    private MessagesRepository messagesRepository;

    public void execute(MessageDTO messageDTO) {
        MessageModel messageModel = new MessageModel();
        messageModel.setMessageContent(messageDTO.getMessageContent());
        messageModel.setSenderId(messageDTO.getSenderId());
        messageModel.setChatId(messageDTO.getChatId());
        messageModel.setMessageUID(messageDTO.getMessageUID());
        messageModel.setMessageRead(messageDTO.isMessageRead());
        messagesRepository.save(messageModel);
        kafkaTemplate.send("messages", messageDTO.getMessageContent());

    }



    private boolean

}
