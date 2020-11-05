package com.borikov.bullfinch.controller.command.impl.page;

import com.borikov.bullfinch.controller.PagePath;
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
 * The {@code BrowseTattooPageCommand} class represents browse tattoo page command.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class BrowseTattooPageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final TattooService tattooService = new TattooServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String tattooId = request.getParameter(RequestParameter.TATTOO_ID);
        try {
            Optional<Tattoo> tattoo = tattooService.findTattooByIdCatalog(tattooId);
            if (tattoo.isPresent()) {
                request.setAttribute(RequestParameter.TATTOO, tattoo.get());
                page = PagePath.TATTOO;
            } else {
                request.setAttribute(RequestParameter.TATTOO_FIND_ERROR_MESSAGE, true);
                page = PagePath.MESSAGE;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while browsing tattoo page", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
