package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestAttribute;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.model.entity.Tattoo;
import com.borikov.bullfinch.model.exception.ServiceException;
import com.borikov.bullfinch.model.service.TattooService;
import com.borikov.bullfinch.model.service.impl.TattooServiceImpl;
import com.borikov.bullfinch.util.XssSecurity;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The {@code FindTattoosAdminCommand} class represents find tattoos admin command.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class FindTattoosAdminCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final TattooService tattooService = new TattooServiceImpl();
    private static final int FIRST_PAGE_NUMBER = 1;
    private static final int TATTOOS_AMOUNT_ON_PAGE = 3;

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String tattooName = request.getParameter(RequestParameter.TATTOO_NAME);
        try {
            String tattooNameSecured = XssSecurity.secure(tattooName);
            List<Tattoo> tattoos = tattooService.findTattoosByNameSubstring(tattooNameSecured);
            request.setAttribute(RequestAttribute.TATTOOS, tattoos);
            request.setAttribute(RequestAttribute.ALL_TATTOOS, true);
            request.setAttribute(RequestAttribute.TATTOO_NAME, tattooNameSecured);
            request.setAttribute(RequestAttribute.PAGE_AMOUNT,
                    Math.ceil((double) tattoos.size() / TATTOOS_AMOUNT_ON_PAGE));
            request.setAttribute(RequestAttribute.PAGE_NUMBER, FIRST_PAGE_NUMBER);
            request.setAttribute(RequestAttribute.TATTOOS_AMOUNT_ON_PAGE, TATTOOS_AMOUNT_ON_PAGE);
            page = PagePath.TATTOOS_ADMIN;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while finding tattoos", e);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
