package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestAttribute;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.model.entity.Discount;
import com.borikov.bullfinch.model.entity.Order;
import com.borikov.bullfinch.model.entity.User;
import com.borikov.bullfinch.model.exception.ServiceException;
import com.borikov.bullfinch.model.service.DiscountService;
import com.borikov.bullfinch.model.service.OrderService;
import com.borikov.bullfinch.model.service.UserService;
import com.borikov.bullfinch.model.service.impl.DiscountServiceImpl;
import com.borikov.bullfinch.model.service.impl.OrderServiceImpl;
import com.borikov.bullfinch.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * The {@code DeleteDiscountCommand} class represents delete discount command.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class DeleteDiscountCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final DiscountService discountService = new DiscountServiceImpl();
    private static final OrderService orderService = new OrderServiceImpl();
    private static final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String discountId = request.getParameter(RequestParameter.DISCOUNT_ID);
        String login = request.getParameter(RequestParameter.LOGIN);
        try {
            if (discountService.removeDiscount(discountId)) {
                Optional<User> user = userService.findUserByLogin(login);
                if (user.isPresent()) {
                    request.setAttribute(RequestAttribute.USER, user.get());
                    List<Order> orders = orderService.findOrdersByUserLogin(login);
                    List<Discount> discounts = discountService.findDiscountsByUserLogin(login);
                    request.setAttribute(RequestAttribute.ORDERS, orders);
                    request.setAttribute(RequestAttribute.DISCOUNTS, discounts);
                    page = PagePath.PROFILE_ADMIN;
                } else {
                    request.setAttribute(RequestAttribute.USER_FIND_ERROR_MESSAGE, true);
                    page = PagePath.MESSAGE;
                }
            } else {
                request.setAttribute(RequestAttribute.DISCOUNT_DELETE_ERROR_MESSAGE, true);
                page = PagePath.MESSAGE;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while deleting discount", e);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
