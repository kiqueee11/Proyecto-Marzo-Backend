package com.flashmeet.messages.converter;

import com.flashmeet.messages.DTOs.MessageDTO;
import com.flashmeet.messages.model.MessageModel;

public class MessageConverter {

    public static MessageDTO modelToDTO(MessageModel messageModel) {

        MessageDTO messageDTO = new MessageDTO.Builder().senderId(messageModel.getSenderId())
                .chatId(messageModel.getChatId()).messageContent(messageModel.getMessageContent())
                .messageUID(messageModel.getMessageUID()).isMessageRead(messageModel.isMessageRead()).build();

        return messageDTO;

    }

    public static MessageModel dtoToModel(MessageDTO messageDTO) {

        MessageModel messageModel = new MessageModel();
        messageModel.setSenderId(messageDTO.getSenderId());
        messageModel.setChatId(messageDTO.getChatId());
        messageModel.setMessageContent(messageDTO.getMessageContent());
        messageModel.setMessageUID(messageDTO.getMessageUID());
        messageModel.setMessageRead(messageDTO.isMessageRead());

        return messageModel;

    }

}
