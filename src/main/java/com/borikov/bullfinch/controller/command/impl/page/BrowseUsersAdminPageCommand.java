package com.borikov.bullfinch.controller.command.impl.page;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.model.entity.User;
import com.borikov.bullfinch.model.exception.ServiceException;
import com.borikov.bullfinch.model.service.UserService;
import com.borikov.bullfinch.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The {@code BrowseUsersAdminPageCommand} class represents browse users admin page command.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class BrowseUsersAdminPageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final UserService userService = new UserServiceImpl();
    private static final int FIRST_PAGE_NUMBER = 1;
    private static final int USERS_AMOUNT_ON_PAGE = 10;

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        try {
            List<User> users = userService.findAllUsers();
            request.setAttribute(RequestParameter.USERS, users);
            request.setAttribute(RequestParameter.PAGE_AMOUNT,
                    Math.ceil((double) users.size() / USERS_AMOUNT_ON_PAGE));
            request.setAttribute(RequestParameter.PAGE_NUMBER, FIRST_PAGE_NUMBER);
            request.setAttribute(RequestParameter.USERS_AMOUNT_ON_PAGE, USERS_AMOUNT_ON_PAGE);
            page = PagePath.USERS_ADMIN;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while browsing users page", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
