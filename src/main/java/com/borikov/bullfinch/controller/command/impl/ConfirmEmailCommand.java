package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.model.exception.ServiceException;
import com.borikov.bullfinch.model.service.UserService;
import com.borikov.bullfinch.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code ConfirmEmailCommand} class represents confirm email command.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class ConfirmEmailCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(RequestParameter.LOGIN);
        try {
            if (userService.confirmUserEmail(login)) {
                request.setAttribute(RequestParameter.USER_EMAIL_CONFIRM_POSITIVE_MESSAGE, true);
            } else {
                request.setAttribute(RequestParameter.USER_EMAIL_CONFIRM_ERROR_MESSAGE, true);
            }
            page = PagePath.MESSAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while confirming email", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
