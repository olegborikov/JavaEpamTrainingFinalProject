package com.borikov.bullfinch.controller.command;

import com.borikov.bullfinch.controller.command.impl.*;

public enum CommandType {
    LOGIN_COMMAND(new LoginCommand()),
    LOGOUT_COMMAND(new LogoutCommand()),
    REGISTRATION_COMMAND(new RegistrationCommand()),
    BROWSE_REGISTRATION_PAGE_COMMAND(new BrowseRegistrationPageCommand()),
    BROWSE_LOGIN_PAGE_COMMAND(new BrowseLoginPageCommand()),
    BROWSE_HOME_PAGE_COMMAND(new BrowseHomePageCommand()),
    CONFIRM_EMAIL_COMMAND(new ConfirmEmailCommand()),
    BROWSE_CATALOG_PAGE_COMMAND(new BrowseCatalogPageCommand()),
    FIND_TATTOO_COMMAND(new FindTattooCommand()),
    SWITCH_LOCALE_COMMAND(new SwitchLocaleCommand()),
    BROWSE_TATTOO_PAGE_COMMAND(new BrowseTattooPageCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
