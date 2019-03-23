package com.mathodcoast.telebot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MessageProcessorImpl implements MessageProcessor {

    private MessageMapper messageMapper = new MessageMapperImpl();

    @Override
    public SendMessage processUpdate(Update update) {

        String userFirstName = update.getMessage().getFrom().getFirstName();
        Message userMessageObj = update.getMessage();

        System.out.println("\n>> User first name: " + userFirstName);

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
