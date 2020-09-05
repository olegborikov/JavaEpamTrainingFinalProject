package com.borikov.bullfinch.controller.command;

import com.borikov.bullfinch.controller.command.impl.*;

public enum CommandType {
    LOGIN_COMMAND(new LoginCommand()),
    LOGOUT_COMMAND(new LogoutCommand()),
    REGISTRATION_COMMAND(new RegistrationCommand()),
    BROWSE_REGISTRATION_COMMAND(new BrowseRegistrationCommand()),
    BROWSE_LOGIN_COMMAND(new BrowseLoginCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
