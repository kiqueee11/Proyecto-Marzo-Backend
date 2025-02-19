package com.flashmeet.messages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flashmeet.messages.model.MessageModel;

@Repository
public interface MessagesRepository extends JpaRepository<MessageModel, Long> {


    
}
