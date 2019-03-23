package com.mathodcoast.telebot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessageMapper {

    boolean containsCommand(String command);

    boolean isAwaitingForData(String command);

    SendMessage handleCommand(Message message);

    SendMessage handleData(Message message);
}
