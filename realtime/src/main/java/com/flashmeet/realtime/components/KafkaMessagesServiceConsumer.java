package com.flashmeet.realtime.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessagesServiceConsumer {
    @Autowired
    private RealtimeWebSocketHandler realtimeWebSocketHandler;

 
    @KafkaListener(topics = "messages", groupId = "realtime")
    public void consume(String message) {

        realtimeWebSocketHandler.sendToClients(message);
    }

}
