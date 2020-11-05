package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.model.exception.ServiceException;
import com.borikov.bullfinch.model.service.TattooService;
import com.borikov.bullfinch.model.service.impl.TattooServiceImpl;
import com.borikov.bullfinch.util.PhotoFileManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code DeleteTattooCommand} class represents delete tattoo command.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class DeleteTattooCommand implements Command {
    private static final TattooService tattooService = new TattooServiceImpl();
    private static final PhotoFileManager photoFileManager = new PhotoFileManager();
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String tattooId = request.getParameter(RequestParameter.TATTOO_ID);
        String imageId = request.getParameter(RequestParameter.IMAGE_ID);
        String imageName = request.getParameter(RequestParameter.IMAGE_NAME);
        try {
            if (tattooService.removeTattoo(tattooId, imageId)) {
                photoFileManager.delete(imageName);
                request.setAttribute(RequestParameter.TATTOO_DELETE_CONFIRM_MESSAGE, true);
            } else {
                request.setAttribute(RequestParameter.TATTOO_DELETE_ERROR_MESSAGE, true);
            }
            page = PagePath.MESSAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while deleting tattoo", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR505;
        }
        return page;
    }
}
