package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestAttributeHandler;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.service.TattooService;
import com.borikov.bullfinch.service.impl.TattooServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class PaginationCatalogCommand implements Command {
    private static final int AMOUNT_OF_TATTOOS_ON_PAGE = 3;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final TattooService tattooService = new TattooServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        try {
            String pageNumberString = request.getParameter(RequestParameter.PAGE_NUMBER);
            int pageNumber = Integer.parseInt(pageNumberString);
            HttpSession session = request.getSession();
            RequestAttributeHandler requestAttributeHandler =
                    (RequestAttributeHandler) session.getAttribute(RequestParameter.REQUEST_ATTRIBUTE_HANDLER);
            Map<String, Object> attributes = requestAttributeHandler.getAttributes();
            String tattooName = (String) attributes.get(RequestParameter.TATTOO_NAME);
            List<Tattoo> allTattoos;
            if (tattooName == null) {
                allTattoos = tattooService.findAllTattoosCatalog();
            } else {
                allTattoos = tattooService.findTattoosByNameCatalog(tattooName);
            }
            List<Tattoo> tattoos = allTattoos.subList(AMOUNT_OF_TATTOOS_ON_PAGE * (pageNumber - 1),
                    Math.min(AMOUNT_OF_TATTOOS_ON_PAGE * pageNumber, allTattoos.size()));
            request.setAttribute(RequestParameter.PAGE_AMOUNT,
                    Math.ceil((double) allTattoos.size() / AMOUNT_OF_TATTOOS_ON_PAGE));
            request.setAttribute(RequestParameter.TATTOOS, tattoos);
            request.setAttribute(RequestParameter.PAGE_NUMBER, pageNumber);
            request.setAttribute(RequestParameter.TATTOO_NAME, tattooName);
            page = PagePath.CATALOG;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while paginate tattoos", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR505;
        }
        return page;
    }
}
