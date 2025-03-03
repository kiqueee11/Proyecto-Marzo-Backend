package com.flashmeet.chat.chat.Usecases;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flashmeet.chat.chat.exceptions.ChatException;
import com.flashmeet.chat.chat.exceptions.ErrCodes;
import com.flashmeet.chat.chat.model.ChatModel;
import com.flashmeet.chat.chat.repository.ChatRepository;

@Service
public class RevealChatIdentityUseCase {

    private ChatRepository chatRepository;

    public void execute(String chatId) {

        ChatModel chat = chatRepository.findByChatId(chatId).orElse(null);
        if (chat == null) {

            throw new ChatException(ErrCodes.THE_CHAT_REQUESTED_DOES_NOT_EXIST, "El chat solicitado no existe",
                    HttpStatus.NOT_FOUND);
        }

        if (chat.isUser1wantsToRevealIdentity() != true || chat.isUser2wantstoRevealIdentity() != true) {

            throw new ChatException(ErrCodes.BOTH_USERS_MUST_WANT_TO_REVEAL_IDENTITY,
                    "Ambos usuarios deben querer revelar su identidad",
                    HttpStatus.CONFLICT);
        }

        chat.setChatAnnonimous(false);
        chatRepository.save(chat);

    }

}
