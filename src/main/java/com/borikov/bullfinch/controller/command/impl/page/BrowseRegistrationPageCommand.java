package com.borikov.bullfinch.controller.command.impl.page;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Browse registration page command.
 */
public class BrowseRegistrationPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.REGISTRATION;
    }
}
