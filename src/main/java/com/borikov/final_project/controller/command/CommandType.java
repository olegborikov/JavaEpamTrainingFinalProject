package com.borikov.final_project.controller.command;

import com.borikov.final_project.controller.command.impl.LoginCommand;
import com.borikov.final_project.controller.command.impl.LogoutCommand;

public enum CommandType {
    LOGIN_COMMAND(new LoginCommand()),
    LOGOUT_COMMAND(new LogoutCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
