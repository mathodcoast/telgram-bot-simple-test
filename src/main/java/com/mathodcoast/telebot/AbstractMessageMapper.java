package com.mathodcoast.telebot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class AbstractMessageMapper {

    private Map<String, Function<Message, SendMessage>> commandHandlers = new HashMap<>();
    private Map<String, Function<Message, SendMessage>> incomeDataHandlers = new HashMap<>();

    public Map<String, Function<Message, SendMessage>> getCommandHandlers() {
        return commandHandlers;
    }

    public Map<String, Function<Message, SendMessage>> getIncomeDataHandlers() {
        return incomeDataHandlers;
    }
}
