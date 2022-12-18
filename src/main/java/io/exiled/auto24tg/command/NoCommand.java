package io.exiled.auto24tg.command;

import io.exiled.auto24tg.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * No {@link Command}.
 *
 * @author Vassili Moskaljov
 * @version 1.0
 */
public class NoCommand implements Command{
    private final SendBotMessageService sendBotMessageService;
    public static final String NO_MESSAGE = "I am allowing commands starting with slash(/).\n" +
            "To chech list of all commands type /help";


    public NoCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), NO_MESSAGE);
    }
}
