package com.borikov.bullfinch.controller.command.impl.page;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestAttribute;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.model.entity.Tattoo;
import com.borikov.bullfinch.model.exception.ServiceException;
import com.borikov.bullfinch.model.service.TattooService;
import com.borikov.bullfinch.model.service.impl.TattooServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * The {@code BrowseTattooAdminPageCommand} class represents browse tattoo admin page command.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class BrowseTattooAdminPageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final TattooService tattooService = new TattooServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String tattooId = request.getParameter(RequestParameter.TATTOO_ID);
        try {
            Optional<Tattoo> tattoo = tattooService.findTattooById(tattooId);
            if (tattoo.isPresent()) {
                request.setAttribute(RequestAttribute.TATTOO, tattoo.get());
                page = PagePath.TATTOO_ADMIN;
            } else {
                request.setAttribute(RequestAttribute.TATTOO_FIND_ERROR_MESSAGE, true);
                page = PagePath.MESSAGE;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while browsing tattoo admin page", e);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
