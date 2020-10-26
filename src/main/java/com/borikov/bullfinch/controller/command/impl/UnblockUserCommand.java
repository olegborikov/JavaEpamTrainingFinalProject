package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.entity.Discount;
import com.borikov.bullfinch.entity.Order;
import com.borikov.bullfinch.entity.User;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.service.DiscountService;
import com.borikov.bullfinch.service.OrderService;
import com.borikov.bullfinch.service.UserService;
import com.borikov.bullfinch.service.impl.DiscountServiceImpl;
import com.borikov.bullfinch.service.impl.OrderServiceImpl;
import com.borikov.bullfinch.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class UnblockUserCommand implements Command {
    private static final UserService userService = new UserServiceImpl();
    private static final Logger LOGGER = LogManager.getLogger();
    private static final OrderService orderService = new OrderServiceImpl();
    private static final DiscountService discountService = new DiscountServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(RequestParameter.LOGIN);
        try {
            if (userService.unblockUser(login)) {
                Optional<User> user = userService.findUserByLogin(login);
                if (user.isPresent()) {
                    request.setAttribute(RequestParameter.USER, user.get());
                    List<Order> orders = orderService.getOrdersByUserLogin(login);
                    List<Discount> discounts =
                            discountService.getDiscountsByUserLogin(login);
                    request.setAttribute(RequestParameter.ORDERS, orders);
                    request.setAttribute(RequestParameter.DISCOUNTS, discounts);
                    page = PagePath.PROFILE_ADMIN;
                } else {
                    request.setAttribute(RequestParameter.USER_FIND_ERROR_MESSAGE, true);
                    page = PagePath.MESSAGE;
                }
            } else {
                request.setAttribute(RequestParameter.USER_UNBLOCK_ERROR_MESSAGE, true);
                page = PagePath.MESSAGE;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while unblocking user", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR505;
        }
        return page;
    }
}
