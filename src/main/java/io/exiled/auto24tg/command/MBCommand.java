package io.exiled.auto24tg.command;

import io.exiled.auto24tg.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MBCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    public final static String START_MESSAGE = "Initializing difference search for Mercedes-Benz make";

    public MBCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}
