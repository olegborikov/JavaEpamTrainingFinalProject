package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.model.entity.Tattoo;
import com.borikov.bullfinch.model.service.TattooService;
import com.borikov.bullfinch.model.service.impl.TattooServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class EditTattooCommand implements Command {
    private static final TattooService tattooService = new TattooServiceImpl();
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String id = request.getParameter(RequestParameter.TATTOO_ID);
        String name = request.getParameter(RequestParameter.NAME);
        String description = request.getParameter(RequestParameter.DESCRIPTION);
        String price = request.getParameter(RequestParameter.PRICE);
        try {
            if (tattooService.editTattoo(id, name, description, price)) {
                Optional<Tattoo> tattoo = tattooService.findTattooById(id);
                if (tattoo.isPresent()) {
                    request.setAttribute(RequestParameter.TATTOO, tattoo.get());
                    page = PagePath.TATTOO_ADMIN;
                } else {
                    request.setAttribute(RequestParameter.TATTOO_FIND_ERROR_MESSAGE, true);
                    page = PagePath.MESSAGE;
                }
            } else {
                page = PagePath.ERROR505;// TODO: 09.10.2020 do smth
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while editing tattoo", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR505;
        }
        return page;
    }
}
