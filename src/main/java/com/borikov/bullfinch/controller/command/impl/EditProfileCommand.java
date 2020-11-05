package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.model.entity.Order;
import com.borikov.bullfinch.model.entity.User;
import com.borikov.bullfinch.model.exception.ServiceException;
import com.borikov.bullfinch.model.service.OrderService;
import com.borikov.bullfinch.model.service.UserService;
import com.borikov.bullfinch.model.service.impl.OrderServiceImpl;
import com.borikov.bullfinch.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * The {@code EditProfileCommand} class represents edit profile command.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class EditProfileCommand implements Command {
    private static final UserService userService = new UserServiceImpl();
    private static final OrderService orderService = new OrderServiceImpl();
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String id = request.getParameter(RequestParameter.USER_ID);
        String email = request.getParameter(RequestParameter.EMAIL);
        String login = request.getParameter(RequestParameter.LOGIN);
        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String secondName = request.getParameter(RequestParameter.SECOND_NAME);
        String phoneNumber = request.getParameter(RequestParameter.PHONE_NUMBER);
        try {
            boolean result = userService.editUser(id, email, login, firstName, secondName, phoneNumber);
            Optional<User> user = userService.findUserByLogin(login);
            if (user.isPresent()) {
                request.setAttribute(RequestParameter.USER, user.get());
                if (result) {
                    page = PagePath.PROFILE;
                    List<Order> orders = orderService.findOrdersByUserLogin(login);
                    request.setAttribute(RequestParameter.ORDERS, orders);
                } else {
                    request.setAttribute(RequestParameter.INCORRECT_DATA_MESSAGE, true);
                    page = PagePath.PROFILE_EDIT;
                }
            } else {
                request.setAttribute(RequestParameter.USER_FIND_ERROR_MESSAGE, true);
                page = PagePath.MESSAGE;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while editing user", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR505;
        }
        return page;
    }
}
