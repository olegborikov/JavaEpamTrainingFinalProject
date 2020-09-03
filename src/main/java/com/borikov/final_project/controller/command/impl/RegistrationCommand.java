package com.borikov.final_project.controller.command.impl;

import com.borikov.final_project.controller.PagePath;
import com.borikov.final_project.controller.RequestParameter;
import com.borikov.final_project.controller.command.Command;
import com.borikov.final_project.exception.ServiceException;
import com.borikov.final_project.service.UserService;
import com.borikov.final_project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        try {
            if (userService.addUser(login, password)) {
                page = PagePath.MAIN;
            } else {
                request.setAttribute(RequestParameter.ERROR_LOGIN_PASSWORD_MESSAGE,
                        "Incorrect login or password");
                page = PagePath.REGISTRATION;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while register user", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR;
        }
        return page;
    }
}
