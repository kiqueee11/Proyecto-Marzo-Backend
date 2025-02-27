package com.flashmeet.messages.Usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flashmeet.messages.DTOs.MessageDTO;
import com.flashmeet.messages.converter.MessageConverter;

import java.util.List;
import com.flashmeet.messages.repository.MessagesRepository;

@Service
public class GetAllMessagesUseCase {

    @Autowired
    private MessagesRepository messagesRepository;

    public List<MessageDTO> execute(String chatId) {

        return messagesRepository.findMessagesByChatId(chatId).stream()
                .map(messageModel -> MessageConverter.modelToDTO(messageModel))
                .toList();

    }

}
