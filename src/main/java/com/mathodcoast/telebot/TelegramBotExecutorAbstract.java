package com.mathodcoast.telebot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public abstract class TelegramBotExecutorAbstract {

    protected SendMessage createSendMessageWithNewKeyboard(String botMessage,ReplyKeyboardMarkup keyboardMarkup){
        SendMessage sendMessage = new SendMessage();
        System.out.print(botMessage);
        sendMessage.setText(botMessage).setReplyMarkup(keyboardMarkup);
        return sendMessage;
    }

    protected void botLog(Message messageObj){
        System.out.println("\n=== " + messageObj.getFrom().getUserName() + ": command << " + messageObj.getText()
                + " >> bot message: ");
    }
}
