package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.model.service.TattooService;
import com.borikov.bullfinch.model.service.impl.TattooServiceImpl;
import com.borikov.bullfinch.util.PhotoFileManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class OfferTattooCommand implements Command {
    private static final PhotoFileManager photoFileManager = new PhotoFileManager();
    private static final TattooService tattooService = new TattooServiceImpl();
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String tattooName = request.getParameter(RequestParameter.NAME);
        String description = request.getParameter(RequestParameter.DESCRIPTION);
        String photoName = (String) request.getAttribute(RequestParameter.PHOTO_NAME);
        String price = request.getParameter(RequestParameter.PRICE);
        HttpSession httpSession = request.getSession();
        String proposedLogin = (String) httpSession.getAttribute(RequestParameter.LOGIN);
        try {
            if (tattooService.offerTattoo(tattooName, description, price, photoName, proposedLogin)) {
                request.setAttribute(RequestParameter.TATTOO_OFFER_CONFIRM_MESSAGE, true);
                page = PagePath.MESSAGE;
            } else {
                request.setAttribute(RequestParameter.INCORRECT_DATA_MESSAGE, true);
                page = PagePath.TATTOO_OFFER;
                photoFileManager.delete(photoName);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while offering tattoo", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR505;
            photoFileManager.delete(photoName);
        }
        return page;
    }
}
