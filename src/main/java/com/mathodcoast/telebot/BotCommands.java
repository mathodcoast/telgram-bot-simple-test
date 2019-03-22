package com.mathodcoast.telebot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class BotCommands {

    private static BotCommands botCommands;

    private Map<String, Function<String,SendMessage>> commands;
    private Map<String, Function<String,SendMessage>> incomeDataHandlers;

    private final String HELLO_BUTTON = "\u26BD Hello!";
    private final String BYE_BUTTON = "\uD83E\uDD11 Bye!";
    private final String HELP_BUTTON = "\uD83D\uDC7B Help";
    private final String WHAT_BUTTON = "\uD83E\uDD14 What are you?";
    private final String SET_NAME_BUTTON = "Set name";


    private BotCommands() {
        commands = new HashMap<>();
        incomeDataHandlers = new HashMap<>();
        setupCommands();
    }

    public static BotCommands valueOf() {
        if (botCommands == null) {
            botCommands = new BotCommands();
        }
        return botCommands;
    }

    public SendMessage getSendMessageByCommand(String userMessage, String chatId) {
        if (commands.containsKey(userMessage)) {
            return commands.get(userMessage).apply(chatId);
        } else {
            if (incomeDataHandlers.containsKey(chatId)){
                return incomeDataHandlers.get(chatId).apply(userMessage);
            } else {
                return new SendMessage().setText("Don't understand such a command").setChatId(chatId).setReplyMarkup(constructMenu());
            }
        }
    }

    private void setupCommands() {
        ReplyKeyboardMarkup mainMenu =
                constructMenu(new String[]{HELLO_BUTTON,BYE_BUTTON},  // Row 1
                              new String[]{HELP_BUTTON,WHAT_BUTTON},  // Row 2
                              new String[]{"But 1",SET_NAME_BUTTON,"But 3"}); // Row 3

        createCommandListener("/start","Aloha!", mainMenu);
        createCommandListener(HELLO_BUTTON,"Putin Huylo!!!", mainMenu);
        createCommandListener(BYE_BUTTON,"NO!", mainMenu);
        createCommandListener(HELP_BUTTON,"Go to HELL!",mainMenu);
        createCommandListener(WHAT_BUTTON,"I am BANDERA!",mainMenu);

        // Create command listener and data handler
        commands.put(SET_NAME_BUTTON,(chatId)-> {
            incomeDataHandlers.put(chatId,(text) -> {
                System.out.println("Can do anything here with user message: " + text);
                return new SendMessage().setText("Good, we have got "+ text).setChatId(chatId).setReplyMarkup(mainMenu);
            });
            return new SendMessage().setChatId(chatId).setText("Enter your Name");
        });
    }

    private ReplyKeyboardMarkup constructMenu(String[]... rows) {
        List<KeyboardRow> keyboard = new ArrayList<>();

        for (String[] row: rows){
            keyboard.add(getKeyboardRow(row));
        }
        return createReplyKeyboardMarkup(keyboard);
    }

    private KeyboardRow getKeyboardRow(String... row) {
        KeyboardRow keyboardRow = new KeyboardRow();
        for(String button: row) {
            keyboardRow.add(new KeyboardButton(button));
        }
        return keyboardRow;
    }

    private ReplyKeyboardMarkup createReplyKeyboardMarkup(List<KeyboardRow> keyboard) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

    private void createCommandListener(String command, String message, ReplyKeyboardMarkup buttons){
        commands.put(command,(chatId) -> createSendMessage(message, command).setReplyMarkup(buttons).setChatId(chatId));
    }

    private SendMessage createSendMessage(String message,String command) {
        SendMessage sendMessage = new SendMessage().setText(message);
        System.out.println("\n=== command << " + command + " >> bot message: " + message);
        System.out.println("We can do something more here");

        return sendMessage;
    }
}
