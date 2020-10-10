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
    FIND_TATTOOS_COMMAND(new FindTattoosCommand()),
    FIND_TATTOOS_ADMIN_COMMAND(new FindTattoosAdminCommand()),
    SWITCH_LOCALE_COMMAND(new SwitchLocaleCommand()),
    BROWSE_TATTOO_PAGE_COMMAND(new BrowseTattooPageCommand()),
    BROWSE_TATTOO_OFFER_PAGE_COMMAND(new BrowseTattooOfferPageCommand()),
    PAGINATION_CATALOG_COMMAND(new PaginationCatalogCommand()),
    OFFER_TATTOO_COMMAND(new OfferTattooCommand()),
    BROWSE_ALL_TATTOOS_ADMIN_PAGE_COMMAND(new BrowseAllTattoosAdminPageCommand()),
    BROWSE_OFFERED_TATTOOS_ADMIN_PAGE_COMMAND(new BrowseOfferedTattoosAdminPageCommand()),
    BROWSE_ARCHIVED_TATTOOS_ADMIN_PAGE_COMMAND(new BrowseArchivedTattoosAdminPageCommand()),
    BROWSE_CATALOG_TATTOOS_ADMIN_PAGE_COMMAND(new BrowseCatalogTattoosAdminPageCommand()),
    BROWSE_TATTOO_ADMIN_PAGE_COMMAND(new BrowseTattooAdminPageCommand()),
    ALLOW_TATTOO_COMMAND(new AllowTattooCommand()),
    DELETE_TATTOO_COMMAND(new DeleteTattooCommand()),
    ARCHIVE_TATTOO_COMMAND(new ArchiveTattooCommand()),
    UNARCHIVE_TATTOO_COMMAND(new UnarchiveTattooCommand()),
    BROWSE_TATTOO_EDIT_PAGE_COMMAND(new BrowseTattooEditPageCommand()),
    EDIT_TATTOO_COMMAND(new EditTattooCommand()),
    BROWSE_TATTOO_ADD_PAGE_COMMAND(new BrowseTattooAddPageCommand()),
    ADD_TATTOO_COMMAND(new AddTattooCommand()),
    BROWSE_PROFILE_PAGE_COMMAND(new BrowseProfilePageCommand()),
    BROWSE_USERS_PAGE_COMMAND(new BrowseUsersPageCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
