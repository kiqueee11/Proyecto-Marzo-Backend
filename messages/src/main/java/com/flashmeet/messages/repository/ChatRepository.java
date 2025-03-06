package com.flashmeet.messages.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ChatRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Optional<Map<String, Object>> getChatData(String chatId) {
        String sqlQuery = "SELECT user1_id,user2_id FROM chat WHERE chat_id = ? ";
        List<Map<String, Object>> chatData = jdbcTemplate.queryForList(sqlQuery, chatId);

        if (chatData.size() > 0) {
            return Optional.of(chatData.get(0));
        } else {
            return Optional.empty();
        }

    }

}
