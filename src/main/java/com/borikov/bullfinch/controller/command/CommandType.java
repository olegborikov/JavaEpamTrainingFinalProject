package com.borikov.bullfinch.controller.command;

import com.borikov.bullfinch.controller.command.impl.*;
import com.borikov.bullfinch.controller.command.impl.page.*;

/**
 * The enum Command type.
 */
public enum CommandType {
    /**
     * The Switch locale command.
     */
    SWITCH_LOCALE_COMMAND(new SwitchLocaleCommand()),
    /**
     * The Browse home page command.
     */
    BROWSE_HOME_PAGE_COMMAND(new BrowseHomePageCommand()),

    /**
     * The Login command.
     */
    LOGIN_COMMAND(new LoginCommand()),
    /**
     * The Registration command.
     */
    REGISTRATION_COMMAND(new RegistrationCommand()),
    /**
     * The Confirm email command.
     */
    CONFIRM_EMAIL_COMMAND(new ConfirmEmailCommand()),
    /**
     * The Browse login page command.
     */
    BROWSE_LOGIN_PAGE_COMMAND(new BrowseLoginPageCommand()),
    /**
     * The Browse registration page command.
     */
    BROWSE_REGISTRATION_PAGE_COMMAND(new BrowseRegistrationPageCommand()),
    /**
     * The Browse catalog page command.
     */
    BROWSE_CATALOG_PAGE_COMMAND(new BrowseCatalogPageCommand()),
    /**
     * The Browse tattoo page command.
     */
    BROWSE_TATTOO_PAGE_COMMAND(new BrowseTattooPageCommand()),
    /**
     * The Pagination command.
     */
    PAGINATION_COMMAND(new PaginationCommand()),
    /**
     * The Find tattoos command.
     */
    FIND_TATTOOS_COMMAND(new FindTattoosCommand()),

    /**
     * The Browse balance enrich page command.
     */
    BROWSE_BALANCE_ENRICH_PAGE_COMMAND(new BrowseBalanceEnrichPageCommand()),
    /**
     * The Enrich balance command.
     */
    ENRICH_BALANCE_COMMAND(new EnrichBalanceCommand()),
    /**
     * The Offer tattoo command.
     */
    OFFER_TATTOO_COMMAND(new OfferTattooCommand()),
    /**
     * The Browse tattoo offer page command.
     */
    BROWSE_TATTOO_OFFER_PAGE_COMMAND(new BrowseTattooOfferPageCommand()),
    /**
     * The Order tattoo command.
     */
    ORDER_TATTOO_COMMAND(new OrderTattooCommand()),
    /**
     * The Browse tattoo order page command.
     */
    BROWSE_TATTOO_ORDER_PAGE_COMMAND(new BrowseTattooOrderPageCommand()),
    /**
     * The Browse profile page command.
     */
    BROWSE_PROFILE_PAGE_COMMAND(new BrowseProfilePageCommand()),
    /**
     * The Browse profile edit page command.
     */
    BROWSE_PROFILE_EDIT_PAGE_COMMAND(new BrowseProfileEditPageCommand()),
    /**
     * The Edit profile command.
     */
    EDIT_PROFILE_COMMAND(new EditProfileCommand()),
    /**
     * The Browse order page command.
     */
    BROWSE_ORDER_PAGE_COMMAND(new BrowseOrderPageCommand()),
    /**
     * The Cancel order command.
     */
    CANCEL_ORDER_COMMAND(new CancelOrderCommand()),
    /**
     * The Logout command.
     */
    LOGOUT_COMMAND(new LogoutCommand()),

    /**
     * The Browse all tattoos admin page command.
     */
    BROWSE_ALL_TATTOOS_ADMIN_PAGE_COMMAND(new BrowseAllTattoosAdminPageCommand()),
    /**
     * The Browse catalog tattoos admin page command.
     */
    BROWSE_CATALOG_TATTOOS_ADMIN_PAGE_COMMAND(new BrowseCatalogTattoosAdminPageCommand()),
    /**
     * The Browse offered tattoos admin page command.
     */
    BROWSE_OFFERED_TATTOOS_ADMIN_PAGE_COMMAND(new BrowseOfferedTattoosAdminPageCommand()),
    /**
     * The Browse archived tattoos admin page command.
     */
    BROWSE_ARCHIVED_TATTOOS_ADMIN_PAGE_COMMAND(new BrowseArchivedTattoosAdminPageCommand()),
    /**
     * The Browse discount add page command.
     */
    BROWSE_DISCOUNT_ADD_PAGE_COMMAND(new BrowseDiscountAddPageCommand()),
    /**
     * The Browse profile admin page command.
     */
    BROWSE_PROFILE_ADMIN_PAGE_COMMAND(new BrowseProfileAdminPageCommand()),
    /**
     * The Find tattoos admin command.
     */
    FIND_TATTOOS_ADMIN_COMMAND(new FindTattoosAdminCommand()),
    /**
     * The Browse tattoo admin page command.
     */
    BROWSE_TATTOO_ADMIN_PAGE_COMMAND(new BrowseTattooAdminPageCommand()),
    /**
     * The Allow tattoo command.
     */
    ALLOW_TATTOO_COMMAND(new AllowTattooCommand()),
    /**
     * The Delete tattoo command.
     */
    DELETE_TATTOO_COMMAND(new DeleteTattooCommand()),
    /**
     * The Archive tattoo command.
     */
    ARCHIVE_TATTOO_COMMAND(new ArchiveTattooCommand()),
    /**
     * The Unarchive tattoo command.
     */
    UNARCHIVE_TATTOO_COMMAND(new UnarchiveTattooCommand()),
    /**
     * The Browse tattoo edit page command.
     */
    BROWSE_TATTOO_EDIT_PAGE_COMMAND(new BrowseTattooEditPageCommand()),
    /**
     * The Edit tattoo command.
     */
    EDIT_TATTOO_COMMAND(new EditTattooCommand()),
    /**
     * The Browse tattoo add page command.
     */
    BROWSE_TATTOO_ADD_PAGE_COMMAND(new BrowseTattooAddPageCommand()),
    /**
     * The Add tattoo command.
     */
    ADD_TATTOO_COMMAND(new AddTattooCommand()),
    /**
     * The Block user command.
     */
    BLOCK_USER_COMMAND(new BlockUserCommand()),
    /**
     * The Unblock user command.
     */
    UNBLOCK_USER_COMMAND(new UnblockUserCommand()),
    /**
     * The Submit order command.
     */
    SUBMIT_ORDER_COMMAND(new SubmitOrderCommand()),
    /**
     * The Deny order command.
     */
    DENY_ORDER_COMMAND(new DenyOrderCommand()),
    /**
     * The Find users admin command.
     */
    FIND_USERS_ADMIN_COMMAND(new FindUsersAdminCommand()),
    /**
     * The Find orders admin command.
     */
    FIND_ORDERS_ADMIN_COMMAND(new FindOrdersAdminCommand()),
    /**
     * The Browse order admin page command.
     */
    BROWSE_ORDER_ADMIN_PAGE_COMMAND(new BrowseOrderAdminPageCommand()),
    /**
     * The Add discount command.
     */
    ADD_DISCOUNT_COMMAND(new AddDiscountCommand()),
    /**
     * The Delete discount command.
     */
    DELETE_DISCOUNT_COMMAND(new DeleteDiscountCommand()),
    /**
     * The Browse orders admin page command.
     */
    BROWSE_ORDERS_ADMIN_PAGE_COMMAND(new BrowseOrdersAdminPageCommand()),
    /**
     * The Browse users page command.
     */
    BROWSE_USERS_PAGE_COMMAND(new BrowseUsersPageCommand());

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
