package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.service.OrderService;
import com.borikov.bullfinch.service.TattooService;
import com.borikov.bullfinch.service.impl.OrderServiceImpl;
import com.borikov.bullfinch.service.impl.TattooServiceImpl;
import com.borikov.bullfinch.util.PhotoFileManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class OrderTattooCommand implements Command {
    private static final PhotoFileManager photoFileManager = new PhotoFileManager();
    private static final OrderService orderService = new OrderServiceImpl();
    private static final TattooService tattooService = new TattooServiceImpl();
    private static final Logger LOGGER = LogManager.getLogger();
    private static final boolean IS_ALLOWED_DEFAULT = true;
    private static final boolean IS_ARCHIVED_DEFAULT = false;

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String tattooId = request.getParameter(RequestParameter.TATTOO_ID);
        String date = request.getParameter(RequestParameter.DATE);
        String description = request.getParameter(RequestParameter.DESCRIPTION);
        String price = request.getParameter(RequestParameter.PRICE);
        HttpSession httpSession = request.getSession();
        String userLogin = (String) httpSession.getAttribute(RequestParameter.LOGIN);
        try {
            if (orderService.addOrder(date, description, price, tattooId, userLogin)) {
                request.setAttribute(RequestParameter.TATTOO_ORDER_CONFIRM_MESSAGE, true);
                page = PagePath.MESSAGE;
            } else {
                request.setAttribute(RequestParameter.INCORRECT_DATA_MESSAGE, true);
                Optional<Tattoo> tattoo = tattooService.findTattooByIdAndAllowedAndArchived(
                        tattooId, IS_ALLOWED_DEFAULT, IS_ARCHIVED_DEFAULT);
                if (tattoo.isPresent()) {
                    request.setAttribute(RequestParameter.TATTOO, tattoo.get());
                    page = PagePath.TATTOO_ORDER;
                } else {
                    request.setAttribute(RequestParameter.TATTOO_FIND_ERROR_MESSAGE, true);
                    page = PagePath.MESSAGE;
                }
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while order tattoo", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR;
        }
        return page;
    }
}
