package com.mathodcoast.telebot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    private BotCommands botCommands;

    public Bot(BotCommands botCommands) {
        this.botCommands = botCommands;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String userMessage = update.getMessage().getText();
        String chatId = update.getMessage().getChatId().toString();

        sendMsg(chatId,userMessage);
    }

    private void sendMsg(String chatId,String userMessage) {
        SendMessage sendMessage = botCommands.getSendMessageByCommand(userMessage, chatId);
        sendMessage.enableMarkdown(true);

        try {
            execute(sendMessage);
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
