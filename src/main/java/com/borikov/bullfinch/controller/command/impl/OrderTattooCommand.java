package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class OrderTattooCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String tattooId = request.getParameter(RequestParameter.TATTOO_ID);
        String date = request.getParameter(RequestParameter.DATE);
        String description = request.getParameter(RequestParameter.DESCRIPTION);
        String price = request.getParameter(RequestParameter.PRICE);
        System.out.println(date);
        System.out.println(description);
        System.out.println(price);
        return null;
    }
}
