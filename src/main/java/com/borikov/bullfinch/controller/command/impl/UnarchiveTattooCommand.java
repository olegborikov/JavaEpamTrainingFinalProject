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
import java.util.Optional;

public class UnarchiveTattooCommand implements Command {
    private static final TattooService tattooService = new TattooServiceImpl();
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String tattooId = request.getParameter(RequestParameter.TATTOO_ID);
        try {
            if (tattooService.unarchiveTattoo(tattooId)) {
                Optional<Tattoo> tattoo = tattooService.findTattooById(tattooId);
                if (tattoo.isPresent()) {
                    request.setAttribute(RequestParameter.TATTOO, tattoo.get());
                    page = PagePath.TATTOO_ADMIN;
                } else {
                    request.setAttribute(RequestParameter.TATTOO_FIND_ERROR_MESSAGE, true);
                    page = PagePath.MESSAGE;
                }
            } else {
                request.setAttribute(RequestParameter.TATTOO_UNARCHIVE_ERROR_MESSAGE, true);
                page = PagePath.MESSAGE;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while unallowing tattoo", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR505;
        }
        return page;
    }
}
