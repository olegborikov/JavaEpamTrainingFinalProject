package com.borikov.final_project.controller.command;

import com.borikov.final_project.controller.command.impl.LoginCommand;

public enum CommandType {
    LOGIN_COMMAND(new LoginCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
