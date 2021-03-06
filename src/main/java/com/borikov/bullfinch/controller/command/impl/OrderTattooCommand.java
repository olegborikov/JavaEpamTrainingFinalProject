package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestAttribute;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.SessionAttribute;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.model.entity.Discount;
import com.borikov.bullfinch.model.entity.Tattoo;
import com.borikov.bullfinch.model.exception.ServiceException;
import com.borikov.bullfinch.model.service.DiscountService;
import com.borikov.bullfinch.model.service.OrderService;
import com.borikov.bullfinch.model.service.TattooService;
import com.borikov.bullfinch.model.service.WalletService;
import com.borikov.bullfinch.model.service.impl.DiscountServiceImpl;
import com.borikov.bullfinch.model.service.impl.OrderServiceImpl;
import com.borikov.bullfinch.model.service.impl.TattooServiceImpl;
import com.borikov.bullfinch.model.service.impl.WalletServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The {@code OrderTattooCommand} class represents order tattoo command.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class OrderTattooCommand implements Command {
    private static final OrderService orderService = new OrderServiceImpl();
    private static final TattooService tattooService = new TattooServiceImpl();
    private static final WalletService walletService = new WalletServiceImpl();
    private static final DiscountService discountService = new DiscountServiceImpl();
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String tattooId = request.getParameter(RequestParameter.TATTOO_ID);
        String date = request.getParameter(RequestParameter.DATE);
        String description = request.getParameter(RequestParameter.DESCRIPTION);
        String price = request.getParameter(RequestParameter.PRICE);
        String discountId = request.getParameter(RequestParameter.DISCOUNT_ID);
        HttpSession session = request.getSession();
        String userLogin = (String) session.getAttribute(SessionAttribute.LOGIN);
        try {
            if (walletService.checkBalanceSize(userLogin, price)) {
                if (orderService.addOrder(date, description, price, tattooId, userLogin, discountId)) {
                    request.setAttribute(RequestAttribute.TATTOO_ORDER_CONFIRM_MESSAGE, true);
                    page = PagePath.MESSAGE;
                } else {
                    Optional<Tattoo> tattoo = tattooService.findTattooByIdCatalog(tattooId);
                    if (tattoo.isPresent()) {
                        request.setAttribute(RequestAttribute.TATTOO, tattoo.get());
                        List<Discount> discounts = discountService.findDiscountsByUserLogin(userLogin);
                        request.setAttribute(RequestAttribute.DISCOUNTS, discounts);
                        request.setAttribute(RequestAttribute.CURRENT_DATE, LocalDate.now());
                        request.setAttribute(RequestAttribute.INCORRECT_DATA_MESSAGE, true);
                        page = PagePath.TATTOO_ORDER;
                    } else {
                        request.setAttribute(RequestAttribute.TATTOO_FIND_ERROR_MESSAGE, true);
                        page = PagePath.MESSAGE;
                    }
                }
            } else {
                request.setAttribute(RequestAttribute.TATTOO_ORDER_BALANCE_ERROR_MESSAGE, true);
                page = PagePath.MESSAGE;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while ordering tattoo", e);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
