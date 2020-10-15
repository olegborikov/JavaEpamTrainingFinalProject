package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.service.UserService;
import com.borikov.bullfinch.service.impl.UserServiceImpl;
import com.borikov.bullfinch.util.EmailSenderUtil;
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
        String email = request.getParameter(RequestParameter.EMAIL);
        String login = request.getParameter(RequestParameter.LOGIN);
        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String secondName = request.getParameter(RequestParameter.SECOND_NAME);
        String phoneNumber = request.getParameter(RequestParameter.PHONE_NUMBER);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String confirmedPassword = request.getParameter(RequestParameter.PASSWORD);
        try {
            if (userService.addUser(email, login,
                    firstName, secondName, phoneNumber, password, confirmedPassword)) {
                EmailSenderUtil.sendMessage(email, login, request.getRequestURL().toString());
                request.setAttribute(RequestParameter.USER_EMAIL_CONFIRM_MESSAGE, true);
                page = PagePath.MESSAGE;
            } else {
                request.setAttribute(RequestParameter.INCORRECT_DATA_MESSAGE, true);
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
