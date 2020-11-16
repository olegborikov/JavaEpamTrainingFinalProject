package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestAttribute;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.model.exception.ServiceException;
import com.borikov.bullfinch.model.service.DiscountService;
import com.borikov.bullfinch.model.service.impl.DiscountServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code AddDiscountCommand} class represents add discount command.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class AddDiscountCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final DiscountService discountService = new DiscountServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String userId = request.getParameter(RequestParameter.USER_ID);
        String percent = request.getParameter(RequestParameter.PERCENT);
        try {
            if (discountService.addDiscount(percent, userId)) {
                request.setAttribute(RequestAttribute.DISCOUNT_ADD_CONFIRM_MESSAGE, true);
                page = PagePath.MESSAGE;
            } else {
                request.setAttribute(RequestAttribute.INCORRECT_DATA_MESSAGE, true);
                request.setAttribute(RequestAttribute.USER_ID, userId);
                page = PagePath.TATTOO_OFFER;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while adding discount", e);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
