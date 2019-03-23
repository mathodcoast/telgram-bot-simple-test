package com.mathodcoast.telebot;

import com.mathodcoast.telebot.buttons.BotButtons;
import com.mathodcoast.telebot.executor.TelegramBotExecutorImpl;
import com.mathodcoast.telebot.mapper.MessageMapperImpl;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class BotStarterApp {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        Bot testBot = new Bot(new MessageProcessorImpl(new MessageMapperImpl(new TelegramBotExecutorImpl(new BotButtons()))));
        testBot.setBotUsername("MathodcoastTestBot");
        testBot.setBotToken("827569285:AAHR5pOpFvzHsX5u-HUMUNuz0Q86epmrvSg");

        try {
            telegramBotsApi.registerBot(testBot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
