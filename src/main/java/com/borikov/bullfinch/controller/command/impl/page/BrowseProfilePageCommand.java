package com.borikov.bullfinch.controller.command.impl.page;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestAttribute;
import com.borikov.bullfinch.controller.SessionAttribute;
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
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * The {@code BrowseProfilePageCommand} class represents browse profile page command.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class BrowseProfilePageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final UserService userService = new UserServiceImpl();
    private static final OrderService orderService = new OrderServiceImpl();
    private static final DiscountService discountService = new DiscountServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(SessionAttribute.LOGIN);
        try {
            Optional<User> user = userService.findUserByLogin(login);
            if (user.isPresent()) {
                request.setAttribute(RequestAttribute.USER, user.get());
                List<Order> orders = orderService.findOrdersByUserLogin(login);
                List<Discount> discounts = discountService.findDiscountsByUserLogin(login);
                request.setAttribute(RequestAttribute.ORDERS, orders);
                request.setAttribute(RequestAttribute.DISCOUNTS, discounts);
                page = PagePath.PROFILE;
            } else {
                request.setAttribute(RequestAttribute.USER_FIND_ERROR_MESSAGE, true);
                page = PagePath.MESSAGE;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while browsing profile page", e);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
