package io.exiled.auto24tg;

import io.exiled.auto24tg.bot.Auto24Bot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class Auto24tgApplication {

    public static void main(String[] args) {
        SpringApplication.run(Auto24tgApplication.class, args);
//        // Register our bot
//        try {
//            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//            botsApi.registerBot(new Auto24Bot());
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
    }

}
