package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
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
 * The type Add discount command.
 */
public class AddDiscountCommand implements Command {
    private static final DiscountService discountService = new DiscountServiceImpl();
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String userId = request.getParameter(RequestParameter.USER_ID);
        String discountPercent = request.getParameter(RequestParameter.DISCOUNT_PERCENT);
        try {
            if (discountService.addDiscount(discountPercent, userId)) {
                request.setAttribute(RequestParameter.DISCOUNT_ADD_CONFIRM_MESSAGE, true);
                page = PagePath.MESSAGE;
            } else {
                request.setAttribute(RequestParameter.INCORRECT_DATA_MESSAGE, true);
                request.setAttribute(RequestParameter.USER_ID, userId);
                page = PagePath.TATTOO_OFFER;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while adding discount", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR505;
        }
        return page;
    }
}
