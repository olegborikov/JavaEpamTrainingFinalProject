package com.borikov.bullfinch.controller.command;

import com.borikov.bullfinch.controller.command.impl.*;
import com.borikov.bullfinch.controller.command.impl.page.*;

/**
 * The {@code CommandType} enum represents command type .
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public enum CommandType {
    SWITCH_LOCALE_COMMAND(new SwitchLocaleCommand()),
    BROWSE_HOME_PAGE_COMMAND(new BrowseHomePageCommand()),
    LOGIN_COMMAND(new LoginCommand()),
    REGISTRATION_COMMAND(new RegistrationCommand()),
    CONFIRM_EMAIL_COMMAND(new ConfirmEmailCommand()),
    BROWSE_LOGIN_PAGE_COMMAND(new BrowseLoginPageCommand()),
    BROWSE_REGISTRATION_PAGE_COMMAND(new BrowseRegistrationPageCommand()),
    BROWSE_CATALOG_PAGE_COMMAND(new BrowseCatalogPageCommand()),
    BROWSE_TATTOO_PAGE_COMMAND(new BrowseTattooPageCommand()),
    PAGINATION_COMMAND(new PaginationCommand()),
    FIND_TATTOOS_COMMAND(new FindTattoosCommand()),
    BROWSE_BALANCE_ENRICH_PAGE_COMMAND(new BrowseBalanceEnrichPageCommand()),
    ENRICH_BALANCE_COMMAND(new EnrichBalanceCommand()),
    OFFER_TATTOO_COMMAND(new OfferTattooCommand()),
    BROWSE_TATTOO_OFFER_PAGE_COMMAND(new BrowseTattooOfferPageCommand()),
    ORDER_TATTOO_COMMAND(new OrderTattooCommand()),
    BROWSE_TATTOO_ORDER_PAGE_COMMAND(new BrowseTattooOrderPageCommand()),
    BROWSE_PROFILE_PAGE_COMMAND(new BrowseProfilePageCommand()),
    BROWSE_PROFILE_EDIT_PAGE_COMMAND(new BrowseProfileEditPageCommand()),
    EDIT_PROFILE_COMMAND(new EditProfileCommand()),
    BROWSE_ORDER_PAGE_COMMAND(new BrowseOrderPageCommand()),
    CANCEL_ORDER_COMMAND(new CancelOrderCommand()),
    LOGOUT_COMMAND(new LogoutCommand()),
    BROWSE_ALL_TATTOOS_ADMIN_PAGE_COMMAND(new BrowseAllTattoosAdminPageCommand()),
    BROWSE_CATALOG_TATTOOS_ADMIN_PAGE_COMMAND(new BrowseCatalogTattoosAdminPageCommand()),
    BROWSE_OFFERED_TATTOOS_ADMIN_PAGE_COMMAND(new BrowseOfferedTattoosAdminPageCommand()),
    BROWSE_ARCHIVED_TATTOOS_ADMIN_PAGE_COMMAND(new BrowseArchivedTattoosAdminPageCommand()),
    BROWSE_DISCOUNT_ADD_PAGE_COMMAND(new BrowseDiscountAddPageCommand()),
    BROWSE_PROFILE_ADMIN_PAGE_COMMAND(new BrowseProfileAdminPageCommand()),
    FIND_TATTOOS_ADMIN_COMMAND(new FindTattoosAdminCommand()),
    BROWSE_TATTOO_ADMIN_PAGE_COMMAND(new BrowseTattooAdminPageCommand()),
    ALLOW_TATTOO_COMMAND(new AllowTattooCommand()),
    DELETE_TATTOO_COMMAND(new DeleteTattooCommand()),
    ARCHIVE_TATTOO_COMMAND(new ArchiveTattooCommand()),
    UNARCHIVE_TATTOO_COMMAND(new UnarchiveTattooCommand()),
    BROWSE_TATTOO_EDIT_PAGE_COMMAND(new BrowseTattooEditPageCommand()),
    EDIT_TATTOO_COMMAND(new EditTattooCommand()),
    BROWSE_TATTOO_ADD_PAGE_COMMAND(new BrowseTattooAddPageCommand()),
    ADD_TATTOO_COMMAND(new AddTattooCommand()),
    BLOCK_USER_COMMAND(new BlockUserCommand()),
    UNBLOCK_USER_COMMAND(new UnblockUserCommand()),
    SUBMIT_ORDER_COMMAND(new SubmitOrderCommand()),
    DENY_ORDER_COMMAND(new DenyOrderCommand()),
    FIND_USERS_ADMIN_COMMAND(new FindUsersAdminCommand()),
    FIND_ORDERS_ADMIN_COMMAND(new FindOrdersAdminCommand()),
    BROWSE_ORDER_ADMIN_PAGE_COMMAND(new BrowseOrderAdminPageCommand()),
    ADD_DISCOUNT_COMMAND(new AddDiscountCommand()),
    DELETE_DISCOUNT_COMMAND(new DeleteDiscountCommand()),
    BROWSE_ORDERS_ADMIN_PAGE_COMMAND(new BrowseOrdersAdminPageCommand()),
    BROWSE_USERS_ADMIN_PAGE_COMMAND(new BrowseUsersAdminPageCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    /**
     * Gets command.
     *
     * @return the command
     */
    public Command getCommand() {
        return command;
    }
}
