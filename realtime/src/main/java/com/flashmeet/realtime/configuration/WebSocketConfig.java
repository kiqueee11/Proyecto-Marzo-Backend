package com.flashmeet.realtime.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.flashmeet.realtime.components.RealtimeWebSocketHandler;
import com.netflix.discovery.DiscoveryClient;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Bean
    public RealtimeWebSocketHandler webSocketHandler() {
        return new RealtimeWebSocketHandler();
    }

    @Override
    public void registerWebSocketHandlers(@SuppressWarnings("null") WebSocketHandlerRegistry registry) {
        
        registry.addHandler(webSocketHandler(), "/realtime").setAllowedOrigins("*");
    }

    

}
