package com.mathodcoast.telebot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BotButtons {
    public static final String HELLO_BUTTON = "\u26BD Hello!";
    public static final String BYE_BUTTON = "\uD83E\uDD11 Bye!";
    public static final String HELP_BUTTON = "\uD83D\uDC7B Help";
    public static final String WHAT_BUTTON = "\uD83E\uDD14 What are you?";
    public static final String SET_NAME_BUTTON = "\uD83D\uDD8B Set name";
    public static final String TEST_EXECUTION_BUTTON = "⚙ Test execution";
    public static final String OPEN_NEW_MENU = "✅ Open New Menu";

    private Map<String, ReplyKeyboardMarkup> keyboards = new HashMap<>();

    public BotButtons() {
        setupKeyboards();
    }

    private void setupKeyboards(){

        ReplyKeyboardMarkup mainMenu =
                constructMenu(new String[]{HELLO_BUTTON,BYE_BUTTON},  // Row 1
                              new String[]{HELP_BUTTON,WHAT_BUTTON},  // Row 2
                              new String[]{OPEN_NEW_MENU,SET_NAME_BUTTON,TEST_EXECUTION_BUTTON}); // Row 3
        mainMenu.setOneTimeKeyboard(false);

        keyboards.put("main", mainMenu);
    }

    public Map<String, ReplyKeyboardMarkup> getKeyboards() {
        return keyboards;
    }

    private ReplyKeyboardMarkup constructMenu(String[]... rows) {
        List<KeyboardRow> keyboard = new ArrayList<>();

        for (String[] row : rows) {
            keyboard.add(getKeyboardRow(row));
        }
        return createReplyKeyboardMarkup(keyboard);
    }

    private KeyboardRow getKeyboardRow(String... row) {
        KeyboardRow keyboardRow = new KeyboardRow();
        for (String button : row) {
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

}
