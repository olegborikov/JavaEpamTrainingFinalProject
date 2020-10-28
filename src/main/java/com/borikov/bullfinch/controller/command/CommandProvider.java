package com.borikov.bullfinch.controller.command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Command DEFAULT_COMMAND = CommandType.BROWSE_HOME_PAGE_COMMAND.getCommand();

    private CommandProvider() {
    }

    public static Command defineCommand(String commandName) {
        Command currentCommand;
        if (commandName != null && !commandName.isBlank()) {
            try {
                CommandType currentType = CommandType.valueOf(commandName.toUpperCase());
                currentCommand = currentType.getCommand();
            } catch (IllegalArgumentException e) {
                LOGGER.log(Level.ERROR, "Incorrect command type: {}", commandName, e);
                currentCommand = DEFAULT_COMMAND;
            }
        } else {
            currentCommand = DEFAULT_COMMAND;
        }
        return currentCommand;
    }
}
