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
    BROWSE_TATTOO_PAGE_COMMAND(new BrowseTattooPageCommand()),
    BROWSE_TATTOO_OFFER_PAGE_COMMAND(new BrowseTattooOfferPageCommand()),
    PAGINATION_CATALOG_COMMAND(new PaginationCatalogCommand()),
    OFFER_TATTOO_COMMAND(new OfferTattooCommand()),
    BROWSE_ALL_TATTOOS_PAGE_COMMAND(new BrowseAllTattoosPageCommand()),
    BROWSE_OFFERED_TATTOOS_PAGE_COMMAND(new BrowseOfferedTattoosPageCommand()),
    BROWSE_ARCHIVED_TATTOOS_PAGE_COMMAND(new BrowseArchivedTattoosPageCommand()),
    BROWSE_USERS_PAGE_COMMAND(new BrowseUsersPageCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
