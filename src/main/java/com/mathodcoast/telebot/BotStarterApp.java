package com.mathodcoast.telebot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class BotStarterApp {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        Bot testBot = new Bot(BotCommands.valueOf());
        try {
            telegramBotsApi.registerBot(testBot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
