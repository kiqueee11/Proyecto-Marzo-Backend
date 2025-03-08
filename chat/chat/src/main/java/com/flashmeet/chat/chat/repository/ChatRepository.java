package com.flashmeet.chat.chat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flashmeet.chat.chat.model.ChatModel;

@Repository

public interface ChatRepository extends JpaRepository<ChatModel, Long> {


    Optional<ChatModel> findByChatId(String chatId);
    Optional<Iterable<ChatModel>> findByUser1Id(String userId);
    Optional<Iterable<ChatModel>> findByUser2Id(String userId);
    
    
}
