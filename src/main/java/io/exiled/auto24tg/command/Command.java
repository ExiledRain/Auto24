package io.exiled.auto24tg.command;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Command interface for handling telegram-bot commands
 *
 * @author Vassili Moskaljov
 * @vers
 * */
public interface Command {
    /*
     * Main method, which is executing command logic
     * */
    void execute(Update update);
}
