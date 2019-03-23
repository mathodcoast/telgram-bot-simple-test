package com.mathodcoast.telebot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class TelegramBotExecutorImpl extends TelegramBotExecutorAbstract implements TelegramBotExecutor{

    private BotButtons botButtons = new BotButtons();

    public TelegramBotExecutorImpl() {
    }

    @Override
    public SendMessage someTestExecution(Message messageObj) {
        botLog(messageObj);
        return createSendMessageWithNewKeyboard("Message object toString in console", null);
    }

    @Override
    public SendMessage openNewMenu(Message messageObj){
        botLog(messageObj);
        return createSendMessageWithNewKeyboard("This is new Menu!", botButtons.getKeyboards().get("main"));
    }

    @Override
    public SendMessage hello(Message messageObj) {
        botLog(messageObj);
        return createSendMessageWithNewKeyboard("Putin Huylo!", null);
    }

    @Override
    public SendMessage bye(Message messageObj) {
        botLog(messageObj);
        return createSendMessageWithNewKeyboard("NO!", null);
    }

    @Override
    public SendMessage help(Message messageObj) {
        botLog(messageObj);
        return createSendMessageWithNewKeyboard("Go to Hell!", null);
    }

    @Override
    public SendMessage what(Message messageObj) {
        botLog(messageObj);
        return createSendMessageWithNewKeyboard("I am BANDERA", null);
    }

    @Override
    public SendMessage saveName(Message messageObj) {
        String data = messageObj.getText();
        System.out.println("Save name: " + data);
        botLog(messageObj);
        return createSendMessageWithNewKeyboard("Name " + data + " has saved",
                botButtons.getKeyboards().get("main"));
    }

    @Override
    public SendMessage startBot(Message messageObj){
        botLog(messageObj);
        return createSendMessageWithNewKeyboard("Aloha!" + messageObj.getFrom().getFirstName(),
                botButtons.getKeyboards().get("main"));
    }
}
