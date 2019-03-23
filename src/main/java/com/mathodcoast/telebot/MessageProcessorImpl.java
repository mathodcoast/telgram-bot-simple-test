package com.mathodcoast.telebot;

import com.mathodcoast.telebot.mapper.MessageMapper;
import com.mathodcoast.telebot.mapper.MessageMapperImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *  This class process updates and invoke necessary 'sendMessage'.
* */

public class MessageProcessorImpl implements MessageProcessor {

    private MessageMapper messageMapper;

    public MessageProcessorImpl(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @Override
    public SendMessage processUpdate(Update update) {
        Message userMessageObj = update.getMessage();
        return getSendMessageByCommand(userMessageObj).enableMarkdown(false);
    }

    private SendMessage getSendMessageByCommand(Message userMessageObj) {
        String userMessage = userMessageObj.getText();
        String chatId = userMessageObj.getChatId().toString();
        SendMessage sendMessage;

        if (messageMapper.containsCommand(userMessage)) {
            sendMessage = messageMapper.handleCommand(userMessageObj);
        } else {
            if (messageMapper.isAwaitingForData(chatId)) {
                sendMessage = messageMapper.handleData(userMessageObj);
            } else {
                sendMessage = invalidUserMessage();
            }
        }
        sendMessage.setChatId(chatId);
        return sendMessage;
    }

    private SendMessage invalidUserMessage() {
        return new SendMessage().setText("Don't understand such a command");
    }
}
