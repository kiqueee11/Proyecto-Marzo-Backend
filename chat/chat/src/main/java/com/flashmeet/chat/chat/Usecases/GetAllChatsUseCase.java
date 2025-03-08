package com.flashmeet.chat.chat.Usecases;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flashmeet.chat.chat.model.ChatModel;
import com.flashmeet.chat.chat.repository.ChatRepository;

@Service
public class GetAllChatsUseCase {

    private ChatRepository chatRepository;

    public GetAllChatsUseCase(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public List<ChatModel> execute(String userId) {

        Optional<Iterable<ChatModel>> chats1 = chatRepository.findByUser1Id(userId);
        Optional<Iterable<ChatModel>> chats2 = chatRepository.findByUser2Id(userId);
        List<ChatModel> result = new ArrayList<>();
        if (chats1.isPresent()) {
            chats1.get().forEach(result::add);
        }
        if (chats2.isPresent()) {
            chats2.get().forEach(result::add);
        }
        return result;

    }

}
