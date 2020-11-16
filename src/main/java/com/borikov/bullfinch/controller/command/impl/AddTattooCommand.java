package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestAttribute;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.SessionAttribute;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.model.exception.ServiceException;
import com.borikov.bullfinch.model.service.TattooService;
import com.borikov.bullfinch.model.service.impl.TattooServiceImpl;
import com.borikov.bullfinch.util.PhotoFileManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The {@code AddTattooCommand} class represents add tattoo command.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class AddTattooCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final PhotoFileManager photoFileManager = new PhotoFileManager();
    private static final TattooService tattooService = new TattooServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String tattooName = request.getParameter(RequestParameter.NAME);
        String description = request.getParameter(RequestParameter.DESCRIPTION);
        String photoName = (String) request.getAttribute(RequestAttribute.PHOTO_NAME);
        String price = request.getParameter(RequestParameter.PRICE);
        HttpSession session = request.getSession();
        String proposedLogin = (String) session.getAttribute(SessionAttribute.LOGIN);
        try {
            if (tattooService.addTattoo(tattooName, description, price, photoName, proposedLogin)) {
                request.setAttribute(RequestAttribute.TATTOO_ADD_CONFIRM_MESSAGE, true);
                page = PagePath.MESSAGE;
            } else {
                request.setAttribute(RequestAttribute.INCORRECT_DATA_MESSAGE, true);
                page = PagePath.TATTOO_OFFER;
                photoFileManager.delete(photoName);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while adding tattoo", e);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
            photoFileManager.delete(photoName);
        }
        return page;
    }
}
