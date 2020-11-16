package com.borikov.bullfinch.controller.command.impl.page;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestAttribute;
import com.borikov.bullfinch.controller.SessionAttribute;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.model.entity.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The {@code BrowseHomePageCommand} class represents browse home page command.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class BrowseHomePageCommand implements Command {
    private static final String ENGLISH_LOCALE = "en_US";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionAttribute.ROLE) == null) {
            session.setAttribute(SessionAttribute.ROLE, UserRole.GUEST.getName());
        }
        if (session.getAttribute(SessionAttribute.CURRENT_LOCALE) == null) {
            session.setAttribute(SessionAttribute.CURRENT_LOCALE, ENGLISH_LOCALE);
        }
        return PagePath.HOME;
    }
}
