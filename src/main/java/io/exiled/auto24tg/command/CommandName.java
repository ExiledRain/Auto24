package io.exiled.auto24tg.command;
/**
 * Enumeration for{@link Command}'s
 *
 * @author Vassili Moskaljov
 * @version 1.0
 */
public enum CommandName {
    START("/start"),
    HELP("/help"),
    NO("/no"),
    STOP("/stop");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
