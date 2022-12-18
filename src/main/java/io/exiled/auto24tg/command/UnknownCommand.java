package io.exiled.auto24tg.command;

import io.exiled.auto24tg.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Unknown {@link Command}.
 *
 * @author Vassili Moskaljov
 * @version 1.0
 */
public class UnknownCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    public static final String UNKNOWN_MESSAGE = "I dont understand you \uD83D\uDE1F, type /help to see what i understand.";

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), UNKNOWN_MESSAGE);
    }
}
