package com.flashmeet.realtime.components;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class RealtimeWebSocketHandler extends TextWebSocketHandler {
    private final List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String datos = message.getPayload();
        System.out.println("Mensaje recibido: " + datos);

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Optional<String> userId = getUserId(session);

        if (!userId.isPresent()) {
            return;
        }
        WebSocketSession userSession = session;
        userSession.getAttributes().put("userId", userId.get());
        sessions.add(session);
        System.out.println("Nueva conexión WebSocket establecida: " + session.getAttributes());
    }

    public void sendToClients(String message) {
        for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Optional<String> getUserId(WebSocketSession webSocketSession) {

        URI uri = webSocketSession.getUri();
        if (uri == null) {
            return Optional.empty();
        }

        if (uri.getQuery() == null) {
            return Optional.empty();
        }

        if (uri.getQuery().isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(uri.getQuery().substring(uri.getQuery().indexOf("=")));
    }

}
