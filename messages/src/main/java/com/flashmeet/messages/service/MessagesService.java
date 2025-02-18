package com.flashmeet.messages.service;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.flashmeet.messages.model.MessageModel;
import com.flashmeet.messages.repository.MessagesRepository;
import com.github.andrewoma.dexx.collection.List;
import com.netflix.discovery.DiscoveryClient;

@Service
public class MessagesService {


    private KafkaTemplate<String, String> kafkaTemplate;

    private final String KAFKA_TOPIC = "messages";




    private MessagesRepository messagesRepository;

    public MessagesService(MessagesRepository messagesRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.messagesRepository = messagesRepository;
        this.kafkaTemplate = kafkaTemplate;
    }


    @SuppressWarnings("unchecked")
    public List<MessageModel> getAllMessages() {
        return (List<MessageModel>) messagesRepository.findAll();
    }

    public ResponseEntity<MessageModel>saveMessage(MessageModel message){
        kafkaTemplate.send(KAFKA_TOPIC, message.getMessageContent());
        return ResponseEntity.ok(messagesRepository.save(message));
    }

  /*  public String getConsumerUrlString() {
        return discoveryClient.getInstancesById("realtime").getFirst()
                .getHostName().toString();
    }*/
    
}
