package com.flashmeet.messages.DTOs;

import com.flashmeet.messages.annotations.MessageDTOValidator;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@MessageDTOValidator
@AllArgsConstructor
@Data
public class MessageDTO {

    private String messageContent;

    private String senderId;

    private String chatId;

    private boolean isMessageRead;

    private String messageUID;

    @SuppressWarnings("unused")
    private MessageDTO(Builder builder) {
        this.messageContent = builder.messageContent;
        this.senderId = builder.senderId;
        this.chatId = builder.chatId;
        this.isMessageRead = builder.isMessageRead;
        this.messageUID = builder.messageUID;
    }

    public MessageDTO() {

    }

    public static class Builder {

        private String messageContent;
        private String senderId;
        private String chatId;
        private boolean isMessageRead;
        private String messageUID;

        public Builder messageContent(String messageContent) {
            this.messageContent = messageContent;
            return this;
        }

        public Builder senderId(String senderId) {
            this.senderId = senderId;
            return this;
        }

        public Builder chatId(String chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder isMessageRead(boolean isMessageRead) {
            this.isMessageRead = isMessageRead;
            return this;
        }

        public Builder messageUID(String messageUID) {
            this.messageUID = messageUID;
            return this;
        }

        public MessageDTO build() {
            if(this.messageContent == null || this.senderId == null || this.chatId == null || this.messageUID == null){
                throw new IllegalArgumentException() ;
            }

            return new MessageDTO(this);
        }

    }

}
