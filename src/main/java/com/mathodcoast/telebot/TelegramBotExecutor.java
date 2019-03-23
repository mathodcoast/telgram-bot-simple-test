package com.mathodcoast.telebot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface TelegramBotExecutor {

    SendMessage someTestExecution(Message messageObj);
    SendMessage startBot(Message messageObj);
    SendMessage openNewMenu(Message messageObj);
    SendMessage hello(Message messageObj);
    SendMessage bye(Message messageObj);
    SendMessage help(Message messageObj);
    SendMessage what(Message messageObj);
    SendMessage saveName(Message messageObj);


}
