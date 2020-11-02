package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.model.entity.User;
import com.borikov.bullfinch.model.entity.UserRole;
import com.borikov.bullfinch.model.exception.ServiceException;
import com.borikov.bullfinch.model.service.UserService;
import com.borikov.bullfinch.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        try {
            Optional<User> userOptional = userService.authorizeUser(login, password);
            if (userOptional.isPresent()) {
                HttpSession session = request.getSession();
                User user = userOptional.get();
                if (user.getUserRole().equals(UserRole.ADMIN)) {
                    session.setAttribute(RequestParameter.ROLE, user.getUserRole().getName());
                    session.setAttribute(RequestParameter.LOGIN, user.getLogin());
                    page = PagePath.HOME;
                } else {
                    if (user.isBlocked()) {
                        request.setAttribute(RequestParameter.USER_BLOCKED_MESSAGE, true);
                        page = PagePath.MESSAGE;
                    } else {
                        if (user.isActivated()) {
                            page = PagePath.HOME;
                            session.setAttribute(RequestParameter.ROLE, user.getUserRole().getName());
                            session.setAttribute(RequestParameter.LOGIN, user.getLogin());
                        } else {
                            request.setAttribute(RequestParameter.USER_EMAIL_CONFIRM_MESSAGE, true);
                            page = PagePath.MESSAGE;
                        }
                    }
                }
            } else {
                request.setAttribute(RequestParameter.INCORRECT_DATA_MESSAGE, true);
                page = PagePath.LOGIN;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while logging in user", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR505;
        }
        return page;
    }
}
