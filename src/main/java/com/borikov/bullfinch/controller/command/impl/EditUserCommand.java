package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.entity.Order;
import com.borikov.bullfinch.entity.User;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.service.OrderService;
import com.borikov.bullfinch.service.UserService;
import com.borikov.bullfinch.service.impl.OrderServiceImpl;
import com.borikov.bullfinch.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class EditUserCommand implements Command {
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
            if (userService.editUser(id, email, login, firstName, secondName, phoneNumber)) {
                Optional<User> user = userService.findUserByLogin(login);
                if (user.isPresent()) {
                    request.setAttribute(RequestParameter.USER, user.get());
                    page = PagePath.PROFILE;
                    List<Order> orders = orderService.getOrdersByUserLogin(login);
                    request.setAttribute(RequestParameter.ORDERS, orders);
                } else {
                    request.setAttribute(RequestParameter.USER_FIND_ERROR_MESSAGE, true);
                    page = PagePath.MESSAGE;
                }
            } else {
                page = PagePath.ERROR505;// TODO: 09.10.2020 do smth
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while editing tattoo", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR505;
        }
        return page;
    }
}
