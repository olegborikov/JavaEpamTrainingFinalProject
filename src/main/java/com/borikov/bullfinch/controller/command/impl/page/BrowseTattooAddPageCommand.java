package com.borikov.bullfinch.controller.command.impl.page;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code BrowseTattooAddPageCommand} class represents browse tattoo add page command.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class BrowseTattooAddPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.TATTOO_ADD;
    }
}
