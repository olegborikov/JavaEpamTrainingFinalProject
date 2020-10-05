package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.entity.Tattoo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class PaginationCatalogCommand implements Command {
    private static final int FIRST_PAGE_NUMBER = 1;
    private static final int AMOUNT_OF_TATTOOS_ON_PAGE = 3;

    @Override
    public String execute(HttpServletRequest request) {
        String pageNumberString = request.getParameter(RequestParameter.PAGE_NUMBER);
        int pageNumber = Integer.parseInt(pageNumberString);
        HttpSession session = request.getSession();
        Map<String, Object> requestAttributes =
                (Map<String, Object>) session.getAttribute(RequestParameter.CURRENT_ATTRIBUTE_MAP);
        List<Tattoo> allTattoos = (List<Tattoo>) requestAttributes.get(RequestParameter.ALL_TATTOOS);
        String tattooName = (String) requestAttributes.get(RequestParameter.TATTOO_NAME);
        List<Tattoo> tattoos = allTattoos.subList(AMOUNT_OF_TATTOOS_ON_PAGE * (pageNumber - 1),
                Math.min(AMOUNT_OF_TATTOOS_ON_PAGE * pageNumber, allTattoos.size()));
        request.setAttribute(RequestParameter.ALL_TATTOOS, allTattoos);
        request.setAttribute(RequestParameter.TATTOOS, tattoos);
        request.setAttribute(RequestParameter.PAGE_NUMBER, FIRST_PAGE_NUMBER);
        request.setAttribute(RequestParameter.PAGE_NUMBER, pageNumber);
        request.setAttribute(RequestParameter.TATTOO_NAME, tattooName);
        return PagePath.CATALOG;
    }
}