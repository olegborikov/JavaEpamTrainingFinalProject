package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.model.entity.Order;
import com.borikov.bullfinch.model.exception.ServiceException;
import com.borikov.bullfinch.model.service.OrderService;
import com.borikov.bullfinch.model.service.WalletService;
import com.borikov.bullfinch.model.service.impl.OrderServiceImpl;
import com.borikov.bullfinch.model.service.impl.WalletServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SubmitOrderCommand implements Command {
    private static final OrderService orderService = new OrderServiceImpl();
    private static final WalletService walletService = new WalletServiceImpl();
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String orderId = request.getParameter(RequestParameter.ORDER_ID);
        try {
            if (walletService.checkBalanceSize(orderId)) {
                if (orderService.submitOrder(orderId)) {
                    Optional<Order> order = orderService.findOrderById(orderId);
                    if (order.isPresent()) {
                        request.setAttribute(RequestParameter.ORDER, order.get());
                        page = PagePath.ORDER_ADMIN;
                    } else {
                        request.setAttribute(RequestParameter.ORDER_FIND_ERROR_MESSAGE, true);
                        page = PagePath.MESSAGE;
                    }
                } else {
                    request.setAttribute(RequestParameter.ORDER_SUBMIT_ERROR_MESSAGE, true);
                    page = PagePath.MESSAGE;
                }
            } else {
                request.setAttribute(RequestParameter.TATTOO_ORDER_BALANCE_ERROR_MESSAGE, true);
                page = PagePath.MESSAGE;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while submitting order", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR505;
        }
        return page;
    }
}
