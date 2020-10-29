package com.borikov.bullfinch.controller.command.impl.page;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.model.entity.Order;
import com.borikov.bullfinch.model.service.OrderService;
import com.borikov.bullfinch.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BrowseOrdersPageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final OrderService orderService = new OrderServiceImpl();
    private static final int FIRST_PAGE_NUMBER = 1;
    private static final int OFFERS_AMOUNT_ON_PAGE = 10;

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        try {
            List<Order> orders = orderService.findAllOrders();
            request.setAttribute(RequestParameter.ORDERS, orders);
            request.setAttribute(RequestParameter.PAGE_AMOUNT,
                    Math.ceil((double) orders.size() / OFFERS_AMOUNT_ON_PAGE));
            request.setAttribute(RequestParameter.PAGE_NUMBER, FIRST_PAGE_NUMBER);
            request.setAttribute(RequestParameter.USERS_AMOUNT_ON_PAGE, OFFERS_AMOUNT_ON_PAGE);
            page = PagePath.ORDERS;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while browsing orders page", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR505;
        }
        return page;
    }
}
