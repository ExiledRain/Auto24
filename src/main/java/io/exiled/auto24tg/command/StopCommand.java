package io.exiled.auto24tg.command;

import io.exiled.auto24tg.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Stop {@link Command}.
 *
 * @author Vassili Moskaljov
 * @version 1.0
 */
public class StopCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    public static final String STOP_MESSAGE = "Deactivated all your subscriptions \uD83D\uDE1F.";


    public StopCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);
    }
}
