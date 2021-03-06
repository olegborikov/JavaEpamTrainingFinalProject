package com.borikov.bullfinch.controller.command.impl.page;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestAttribute;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.SessionAttribute;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.model.entity.Discount;
import com.borikov.bullfinch.model.entity.Tattoo;
import com.borikov.bullfinch.model.exception.ServiceException;
import com.borikov.bullfinch.model.service.DiscountService;
import com.borikov.bullfinch.model.service.TattooService;
import com.borikov.bullfinch.model.service.impl.DiscountServiceImpl;
import com.borikov.bullfinch.model.service.impl.TattooServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The {@code BrowseTattooOrderPageCommand} class represents browse tattoo order page command.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class BrowseTattooOrderPageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final TattooService tattooService = new TattooServiceImpl();
    private static final DiscountService discountService = new DiscountServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String tattooId = request.getParameter(RequestParameter.TATTOO_ID);
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(SessionAttribute.LOGIN);
        try {
            Optional<Tattoo> tattoo = tattooService.findTattooByIdCatalog(tattooId);
            if (tattoo.isPresent()) {
                request.setAttribute(RequestAttribute.TATTOO, tattoo.get());
                List<Discount> discounts = discountService.findDiscountsByUserLogin(login);
                request.setAttribute(RequestAttribute.DISCOUNTS, discounts);
                request.setAttribute(RequestAttribute.CURRENT_DATE, LocalDate.now());
                page = PagePath.TATTOO_ORDER;
            } else {
                request.setAttribute(RequestAttribute.TATTOO_FIND_ERROR_MESSAGE, true);
                page = PagePath.MESSAGE;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while browsing tattoo order page", e);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
