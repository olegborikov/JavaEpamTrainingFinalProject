package com.borikov.bullfinch.controller.command;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.borikov.bullfinch.controller.command.CommandType.*;

public enum RolePermission {
    GUEST(Stream.of(
            SWITCH_LOCALE_COMMAND,
            BROWSE_HOME_PAGE_COMMAND,
            LOGIN_COMMAND,
            REGISTRATION_COMMAND,
            CONFIRM_EMAIL_COMMAND,
            BROWSE_LOGIN_PAGE_COMMAND,
            BROWSE_REGISTRATION_PAGE_COMMAND,
            BROWSE_CATALOG_PAGE_COMMAND,
            BROWSE_TATTOO_PAGE_COMMAND,
            PAGINATION_COMMAND,
            FIND_TATTOOS_COMMAND
    ).map(CommandType::getCommand).collect(Collectors.toSet())),
    USER(Stream.of(
            SWITCH_LOCALE_COMMAND,
            BROWSE_HOME_PAGE_COMMAND,
            BROWSE_CATALOG_PAGE_COMMAND,
            BROWSE_TATTOO_PAGE_COMMAND,
            PAGINATION_COMMAND,
            FIND_TATTOOS_COMMAND,
            BROWSE_BALANCE_ENRICH_PAGE_COMMAND,
            ENRICH_BALANCE_COMMAND,
            OFFER_TATTOO_COMMAND,
            BROWSE_TATTOO_OFFER_PAGE_COMMAND,
            ORDER_TATTOO_COMMAND,
            BROWSE_TATTOO_ORDER_PAGE_COMMAND,
            BROWSE_PROFILE_PAGE_COMMAND,
            BROWSE_PROFILE_EDIT_PAGE_COMMAND,
            EDIT_PROFILE_COMMAND,
            BROWSE_ORDER_PAGE_COMMAND,
            CANCEL_ORDER_COMMAND,
            LOGOUT_COMMAND
    ).map(CommandType::getCommand).collect(Collectors.toSet())),
    ADMIN(Stream.of(
            SWITCH_LOCALE_COMMAND,
            BROWSE_HOME_PAGE_COMMAND,
            BROWSE_TATTOO_PAGE_COMMAND,
            PAGINATION_COMMAND,
            LOGOUT_COMMAND,
            BROWSE_ALL_TATTOOS_ADMIN_PAGE_COMMAND,
            BROWSE_CATALOG_TATTOOS_ADMIN_PAGE_COMMAND,
            BROWSE_OFFERED_TATTOOS_ADMIN_PAGE_COMMAND,
            BROWSE_ARCHIVED_TATTOOS_ADMIN_PAGE_COMMAND,
            BROWSE_DISCOUNT_ADD_PAGE_COMMAND,
            BROWSE_PROFILE_ADMIN_PAGE_COMMAND,
            FIND_TATTOOS_ADMIN_COMMAND,
            BROWSE_TATTOO_ADMIN_PAGE_COMMAND,
            ALLOW_TATTOO_COMMAND,
            DELETE_TATTOO_COMMAND,
            ARCHIVE_TATTOO_COMMAND,
            UNARCHIVE_TATTOO_COMMAND,
            BROWSE_TATTOO_EDIT_PAGE_COMMAND,
            EDIT_TATTOO_COMMAND,
            BROWSE_TATTOO_ADD_PAGE_COMMAND,
            ADD_TATTOO_COMMAND,
            BLOCK_USER_COMMAND,
            UNBLOCK_USER_COMMAND,
            SUBMIT_ORDER_COMMAND,
            DENY_ORDER_COMMAND,
            FIND_USERS_ADMIN_COMMAND,
            FIND_ORDERS_ADMIN_COMMAND,
            BROWSE_ORDER_ADMIN_PAGE_COMMAND,
            ADD_DISCOUNT_COMMAND,
            DELETE_DISCOUNT_COMMAND,
            BROWSE_ORDERS_ADMIN_PAGE_COMMAND,
            BROWSE_USERS_PAGE_COMMAND
    ).map(CommandType::getCommand).collect(Collectors.toSet()));

    private final Set<Command> commands;

    RolePermission(Set<Command> commands) {
        this.commands = commands;
    }

    public Set<Command> getCommands() {
        return commands;
    }
}
