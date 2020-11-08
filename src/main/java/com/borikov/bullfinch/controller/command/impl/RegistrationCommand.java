package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.model.exception.ServiceException;
import com.borikov.bullfinch.model.service.UserService;
import com.borikov.bullfinch.model.service.impl.UserServiceImpl;
import com.borikov.bullfinch.util.EmailSender;
import com.borikov.bullfinch.util.RegistrationParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code RegistrationCommand} class represents registration command.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class RegistrationCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        Map<String, String> registrationParameters = new HashMap<>();
        String email = request.getParameter(RequestParameter.EMAIL);
        String login = request.getParameter(RequestParameter.LOGIN);
        registrationParameters.put(RegistrationParameter.EMAIL, email);
        registrationParameters.put(RegistrationParameter.LOGIN, login);
        registrationParameters.put(RegistrationParameter.FIRST_NAME,
                request.getParameter(RequestParameter.FIRST_NAME));
        registrationParameters.put(RegistrationParameter.SECOND_NAME,
                request.getParameter(RequestParameter.SECOND_NAME));
        registrationParameters.put(RegistrationParameter.PHONE_NUMBER,
                request.getParameter(RequestParameter.PHONE_NUMBER));
        registrationParameters.put(RegistrationParameter.PASSWORD,
                request.getParameter(RequestParameter.PASSWORD));
        registrationParameters.put(RegistrationParameter.CONFIRMED_PASSWORD,
                request.getParameter(RequestParameter.CONFIRMED_PASSWORD));
        try {
            if (userService.addUser(registrationParameters)) {
                EmailSender.sendMessage(email, login, request.getRequestURL().toString());
                request.setAttribute(RequestParameter.USER_EMAIL_CONFIRM_MESSAGE, true);
                page = PagePath.MESSAGE;
            } else {
                request.setAttribute(RequestParameter.REGISTRATION_PARAMETERS, registrationParameters);
                page = PagePath.REGISTRATION;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while registering user", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
