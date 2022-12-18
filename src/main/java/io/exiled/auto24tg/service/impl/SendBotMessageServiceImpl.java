package io.exiled.auto24tg.service.impl;

import io.exiled.auto24tg.bot.Auto24Bot;
import io.exiled.auto24tg.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Implementation of {@link SendBotMessageService} interface
 *
 * @author Vassili Moskaljov
 * @version 1.0
 */
public class SendBotMessageServiceImpl implements SendBotMessageService {
    private final Auto24Bot bot;

    public SendBotMessageServiceImpl(Auto24Bot bot) {
        this.bot = bot;
    }


    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
