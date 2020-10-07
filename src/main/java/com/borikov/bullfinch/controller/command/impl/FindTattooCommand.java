package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
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
import java.util.List;

public class FindTattooCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final TattooService tattooService = new TattooServiceImpl();
    private static final int FIRST_PAGE_NUMBER = 1;
    private static final int AMOUNT_OF_TATTOOS_ON_PAGE = 3;
    private static final boolean IS_ALLOWED_DEFAULT = true;
    private static final boolean IS_ARCHIVED_DEFAULT = false;

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String tattooName = request.getParameter(RequestParameter.TATTOO_NAME);
        try {
            List<Tattoo> allTattoos = tattooService.findTattoosByNameAndAllowedAndArchived(
                    tattooName, IS_ALLOWED_DEFAULT, IS_ARCHIVED_DEFAULT);
            List<Tattoo> tattoos = allTattoos.subList(0, Math.min(AMOUNT_OF_TATTOOS_ON_PAGE,
                    allTattoos.size()));
            request.setAttribute(RequestParameter.ALL_TATTOOS, allTattoos);
            request.setAttribute(RequestParameter.TATTOOS, tattoos);
            request.setAttribute(RequestParameter.TATTOO_NAME, tattooName);
            request.setAttribute(RequestParameter.PAGE_NUMBER, FIRST_PAGE_NUMBER);
            page = PagePath.CATALOG;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while finding tattoos by name", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR;
        }
        return page;
    }
}
