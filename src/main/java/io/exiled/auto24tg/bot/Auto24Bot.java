package io.exiled.auto24tg.bot;

import io.exiled.auto24tg.command.CommandContainer;
import io.exiled.auto24tg.service.impl.ExtractPageContentServiceImpl;
import io.exiled.auto24tg.service.impl.SendBotMessageServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import static io.exiled.auto24tg.command.CommandName.NO;

@Component
public class Auto24Bot extends TelegramLongPollingBot {
    public static String COMMAND_PREFIX = "/";
    @Value("${bot.token}")
    private String token = "5975910062:AAEgghkEhLFo1mWotlR4_Ns4_JpJwSqOwXA";
    @Value("${bot.username}")
    private String username = "auto24_exiled_bot";
    private CommandContainer commandContainer;

    public Auto24Bot() {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this), new ExtractPageContentServiceImpl());
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }
    }

    @PostConstruct
    void initBot() {
        // Register our bot
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Auto24Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        System.err.println("Bot initialized");
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
