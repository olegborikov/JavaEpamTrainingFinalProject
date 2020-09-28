package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SwitchLocaleCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String locale = request.getParameter(RequestParameter.NEW_LOCALE);
        HttpSession session = request.getSession();
        session.setAttribute(RequestParameter.CURRENT_LOCALE, locale);
        return (String) session.getAttribute(RequestParameter.CURRENT_PAGE);
    }
}