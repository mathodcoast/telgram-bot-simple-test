package com.mathodcoast.telebot.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBotButtons {

    protected ReplyKeyboardMarkup constructMenu(String[]... rows) {
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
