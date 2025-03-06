package com.flashmeet.chat.chat.Usecases;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flashmeet.chat.chat.exceptions.ChatException;
import com.flashmeet.chat.chat.feignClientInterfaces.UserServiceClient;
import com.flashmeet.chat.chat.model.ChatModel;
import com.flashmeet.chat.chat.repository.ChatRepository;

@ExtendWith(MockitoExtension.class)
public class CreateChatUseCaseTest {

    @Mock
    private ChatRepository chatRepository;

    @Mock
    private UserServiceClient userServiceClient;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private CreateChatUseCase createChatUseCase;

    @BeforeEach

    @Test

    void testCreateChatSuccess() throws Exception {
        String userId1 = "user1";
        String userId2 = "user2";
        String user1Json = "{\"nombre\":\"User 1\",\"imagen1\":\"url1\"}";
        String user2Json = "{\"nombre\":\"User 2\",\"imagen1\":\"url2\"}";
        ResponseEntity<String> user1Response = ResponseEntity.ok(user1Json);
        ResponseEntity<String> user2Response = ResponseEntity.ok(user2Json);
        when(userServiceClient.getUserById(userId1)).thenReturn(java.util.Optional.of(user1Response));
        when(userServiceClient.getUserById(userId2)).thenReturn(java.util.Optional.of(user2Response));

        ChatModel chatModel = new ChatModel();
        when(chatRepository.save(any(ChatModel.class))).thenReturn(chatModel);

        ChatModel result = createChatUseCase.execute(userId1, userId2);

        assert result != null;

    }

    @Test

    void testCreateChatError_UserNotFound() {

        String userId1 = "user1";
        String userId2 = "user2";
        String user1Json = "{\"nombre\":\"User 1\",\"imagen1\":\"url1\"}";
        String user2Json = "{\"nombre\":\"User 2\",\"imagen1\":\"url2\"}";
        when(userServiceClient.getUserById(userId1)).thenReturn(Optional.empty());
        when(userServiceClient.getUserById(userId2)).thenReturn(Optional.empty());
        assertThrows(ChatException.class, () -> createChatUseCase.execute(userId1, userId2));

    }

}
