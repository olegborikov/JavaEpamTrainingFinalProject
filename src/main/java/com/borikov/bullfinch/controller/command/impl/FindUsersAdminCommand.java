package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestAttribute;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.model.entity.User;
import com.borikov.bullfinch.model.exception.ServiceException;
import com.borikov.bullfinch.model.service.UserService;
import com.borikov.bullfinch.model.service.impl.UserServiceImpl;
import com.borikov.bullfinch.util.XssSecurity;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The {@code FindUsersAdminCommand} class represents find users admin command.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class FindUsersAdminCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final UserService userService = new UserServiceImpl();
    private static final int FIRST_PAGE_NUMBER = 1;
    private static final int USERS_AMOUNT_ON_PAGE = 10;

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String searchLogin = request.getParameter(RequestParameter.SEARCH_LOGIN);
        try {
            String searchLoginSecured = XssSecurity.secure(searchLogin);
            List<User> users = userService.findUsersByLoginSubstring(searchLoginSecured);
            request.setAttribute(RequestAttribute.USERS, users);
            request.setAttribute(RequestAttribute.PAGE_AMOUNT,
                    Math.ceil((double) users.size() / USERS_AMOUNT_ON_PAGE));
            request.setAttribute(RequestAttribute.PAGE_NUMBER, FIRST_PAGE_NUMBER);
            request.setAttribute(RequestAttribute.USERS_AMOUNT_ON_PAGE, USERS_AMOUNT_ON_PAGE);
            request.setAttribute(RequestAttribute.SEARCH_LOGIN, searchLoginSecured);
            page = PagePath.USERS_ADMIN;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while finding users", e);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
