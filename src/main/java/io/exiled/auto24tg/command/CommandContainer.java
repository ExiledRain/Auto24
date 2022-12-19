package io.exiled.auto24tg.command;

import com.google.common.collect.ImmutableMap;
import io.exiled.auto24tg.service.SendBotMessageService;
import io.exiled.auto24tg.service.impl.ExtractPageContentServiceImpl;

import static io.exiled.auto24tg.command.CommandName.*;

/**
 * Container of the {@link Command}s, which are using for handling telegram commands.
 *
 * @author Vassili Mosklajov
 * @version 1.0
 */
public class CommandContainer {
    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService, ExtractPageContentServiceImpl extractPageContentService) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService))
                .put(MB.getCommandName(),new MBCommand(sendBotMessageService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService,extractPageContentService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
