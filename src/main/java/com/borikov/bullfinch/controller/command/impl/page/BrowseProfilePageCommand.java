package com.borikov.bullfinch.controller.command.impl.page;

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
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class BrowseProfilePageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final UserService userService = new UserServiceImpl();
    private static final OrderService orderService = new OrderServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession httpSession = request.getSession();
        String login = (String) httpSession.getAttribute(RequestParameter.LOGIN);
        try {
            Optional<User> user = userService.findUserByLogin(login);
            if (user.isPresent()) {
                request.setAttribute(RequestParameter.USER, user.get());
                List<Order> orders = orderService.getOrdersByUserLogin(login);
                request.setAttribute(RequestParameter.ORDERS, orders);
                page = PagePath.PROFILE;
            } else {
                request.setAttribute(RequestParameter.USER_FIND_ERROR_MESSAGE, true);
                page = PagePath.MESSAGE;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while finding tattoo", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR;
        }
        return page;
    }
}
