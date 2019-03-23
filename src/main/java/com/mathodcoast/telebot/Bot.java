package com.mathodcoast.telebot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

private MessageProcessor messageProcessor;

    public Bot(MessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            execute(messageProcessor.processUpdate(update));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "MathodcoastTestBot";
    }

    @Override
    public String getBotToken() {
        return "827569285:AAHR5pOpFvzHsX5u-HUMUNuz0Q86epmrvSg";
    }
}
