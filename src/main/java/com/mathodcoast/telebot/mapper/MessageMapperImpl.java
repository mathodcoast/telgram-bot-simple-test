package com.mathodcoast.telebot.mapper;

import com.mathodcoast.telebot.executor.TelegramBotExecutor;
import com.mathodcoast.telebot.executor.TelegramBotExecutorImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

import static com.mathodcoast.telebot.buttons.BotButtons.*;

/**
 * Here we can connect buttons to their methods.
 */
public class MessageMapperImpl extends AbstractMessageMapper implements MessageMapper {
    private TelegramBotExecutor executor;

    public MessageMapperImpl(TelegramBotExecutor executor) {
        this.executor = executor;
        setupCommands();
    }

    private void setupCommands() {
        getCommandHandlers().put("/start",executor::startBot);
        getCommandHandlers().put(TEST_EXECUTION_BUTTON,executor::someTestExecution);
        getCommandHandlers().put(HELLO_BUTTON,executor::hello);
        getCommandHandlers().put(BYE_BUTTON,executor::bye);
        getCommandHandlers().put(HELP_BUTTON,executor::help);
        getCommandHandlers().put(WHAT_BUTTON,executor::what);
        getCommandHandlers().put(OPEN_NEW_MENU,executor::openNewMenu);

        // Create command listener and data handler
        getCommandHandlers().put(SET_NAME_BUTTON,(messageObj) -> {
            getIncomeDataHandlers().put(messageObj.getChatId().toString(),executor::saveName);
            return new SendMessage().setChatId(messageObj.getChatId()).setText("Enter your Name")
                    .setReplyMarkup(new ReplyKeyboardRemove());
        });
    }

    @Override
    public boolean containsCommand(String command) {
        return getCommandHandlers().containsKey(command);
    }

    @Override
    public boolean isAwaitingForData(String ChatId) {
        return getIncomeDataHandlers().containsKey(ChatId);
    }

    @Override
    public SendMessage handleCommand(Message message) {
        String userMessage = message.getText();
        return getCommandHandlers().get(userMessage).apply(message);
    }

    @Override
    public SendMessage handleData(Message message) {
        String ChatId = message.getChatId().toString();
        return getIncomeDataHandlers().get(ChatId).apply(message);
    }
}
