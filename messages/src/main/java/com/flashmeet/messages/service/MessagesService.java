package com.flashmeet.messages.service;


import org.springframework.stereotype.Service;

import com.flashmeet.messages.Usecases.GetAllMessagesUseCase;
import com.flashmeet.messages.Usecases.SendMessageUseCase;

import lombok.Getter;

@Service
@Getter
public class MessagesService {

    private SendMessageUseCase sendMessageUseCase;

    private GetAllMessagesUseCase getAllMessagesUseCase;

    public MessagesService(
            SendMessageUseCase sendMessageUseCase, GetAllMessagesUseCase getAllMessagesUseCase) {

        this.sendMessageUseCase = sendMessageUseCase;
        this.getAllMessagesUseCase = getAllMessagesUseCase;
    }

}
