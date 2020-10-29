package com.borikov.bullfinch.controller.command.impl.page;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.model.entity.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BrowseHomePageCommand implements Command {
    private static final String ENGLISH_LOCALE = "en_US";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(RequestParameter.ROLE) == null) {
            session.setAttribute(RequestParameter.ROLE, UserRole.GUEST.getName());
        }
        if (session.getAttribute(RequestParameter.CURRENT_LOCALE) == null) {
            session.setAttribute(RequestParameter.CURRENT_LOCALE, ENGLISH_LOCALE);
        }
        return PagePath.HOME;
    }
}
