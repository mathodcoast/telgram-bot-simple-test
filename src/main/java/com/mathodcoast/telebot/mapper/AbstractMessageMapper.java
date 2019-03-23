package com.mathodcoast.telebot.mapper;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

abstract class AbstractMessageMapper {

    private Map<String, Function<Message, SendMessage>> commandHandlers = new HashMap<>();
    private Map<String, Function<Message, SendMessage>> incomeDataHandlers = new HashMap<>();

    Map<String, Function<Message, SendMessage>> getCommandHandlers() {
        return commandHandlers;
    }

    Map<String, Function<Message, SendMessage>> getIncomeDataHandlers() {
        return incomeDataHandlers;
    }
}
