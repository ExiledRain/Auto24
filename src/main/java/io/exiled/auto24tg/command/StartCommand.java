package io.exiled.auto24tg.command;

import io.exiled.auto24tg.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Start {@link Command}.
 *
 * @author Vassili Moskaljov
 * @version 1.0
 */
public class StartCommand implements Command{
    private final SendBotMessageService sendBotMessageService;
    public final static String START_MESSAGE = "КАРМА ДУРАК";

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}
