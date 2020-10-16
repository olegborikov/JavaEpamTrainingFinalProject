package com.borikov.bullfinch.controller.command.impl.page;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class BrowseTattooAddPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.TATTOO_ADD;
    }
}