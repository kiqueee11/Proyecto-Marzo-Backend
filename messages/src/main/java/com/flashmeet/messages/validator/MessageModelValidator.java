package com.flashmeet.messages.validator;

import com.flashmeet.messages.DTOs.MessageDTO;
import com.flashmeet.messages.annotations.MessageDTOValidator;
import com.flashmeet.messages.exceptions.ErrCodes;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MessageModelValidator implements ConstraintValidator<MessageDTOValidator, MessageDTO> {

    @Override
    public boolean isValid(MessageDTO value, ConstraintValidatorContext context) {
        return isMessageValid(value, context);
    }

    private boolean isMessageValid(MessageDTO messageDTO, ConstraintValidatorContext context) {

        System.out.println("VALIDANDO MENSAJE");
        boolean isValid = true;

        if (messageDTO == null) {

            return false;

        }

        if (messageDTO.getMessageContent() == null || messageDTO.getMessageContent().isEmpty()) {
            context.buildConstraintViolationWithTemplate(ErrCodes.MESSAGE_CONTENT_CANNOT_BE_EMPTY)
                    .addPropertyNode("messageContent")
                    .addConstraintViolation();
            isValid &= false;
        }

        if (messageDTO.getMessageContent().length() > 300) {
            context.buildConstraintViolationWithTemplate(ErrCodes.THE_MESSAGE_LENGTH_MUST_BE_UNDER_300_CHARACTERS)
                    .addPropertyNode("messageContent").addConstraintViolation();
            isValid &= false;
        }

        if (messageDTO.getSenderId() == null || messageDTO.getSenderId().isEmpty()) {
            context.buildConstraintViolationWithTemplate(ErrCodes.THE_SENDER_ID_CANNOT_BE_EMPTY)
                    .addPropertyNode("senderId").addConstraintViolation();
            isValid &= false;
        }

        if (messageDTO.getChatId() == null || messageDTO.getChatId().isEmpty()) {
            context.buildConstraintViolationWithTemplate(ErrCodes.CHAT_ID_CANNOT_BE_EMPTY)
                    .addPropertyNode("chatId").addConstraintViolation();
            isValid &= false;
        }

        if (messageDTO.getMessageUID() == null || messageDTO.getMessageUID().isEmpty()) {
            context.buildConstraintViolationWithTemplate(ErrCodes.MESSAGE_UID_CANNOT_BE_EMPTY)
                    .addPropertyNode("messageUID").addConstraintViolation();
            isValid &= false;
        }

        return isValid;
    }

}
