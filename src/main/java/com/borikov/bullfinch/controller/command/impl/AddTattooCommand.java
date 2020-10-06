package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.service.TattooService;
import com.borikov.bullfinch.service.impl.TattooServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddTattooCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final TattooService tattooService = new TattooServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String tattooName = request.getParameter(RequestParameter.NAME);
        String description = request.getParameter(RequestParameter.DESCRIPTION);
        String photoName = (String) request.getAttribute(RequestParameter.PHOTO_NAME);
        try {
            if (tattooService.addTattoo(tattooName, description, photoName)) {
                request.setAttribute(RequestParameter.TATTOO_OFFER_CONFIRM_MESSAGE, true);
                page = PagePath.TATTOO_OFFER_CONFIRM;
            } else {
                request.setAttribute(RequestParameter.ERROR_DATA_MESSAGE, "Incorrect data");
                page = PagePath.TATTOO_OFFER;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while offer tattoo", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR;
        }
        return page;
    }
}
