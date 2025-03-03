package com.flashmeet.chat.chat.Usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flashmeet.chat.chat.exceptions.ChatException;
import com.flashmeet.chat.chat.exceptions.ErrCodes;
import com.flashmeet.chat.chat.model.ChatModel;
import com.flashmeet.chat.chat.repository.ChatRepository;

@Service
public class DeleteChatUseCase {

    @Autowired
    private ChatRepository chatRepository;

    public void execute(Long chatId) {

        ChatModel chat = chatRepository.findById(chatId).orElse(null);

        if (chat == null) {
            throw new ChatException(ErrCodes.THE_CHAT_REQUESTED_DOES_NOT_EXIST, "El chat solicitado no existe",
                    HttpStatus.NOT_FOUND);
        }

        chatRepository.deleteById(chatId);
    }

}
