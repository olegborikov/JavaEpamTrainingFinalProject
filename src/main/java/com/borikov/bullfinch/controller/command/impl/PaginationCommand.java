package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.RequestAttributeHandler;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class PaginationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        RequestAttributeHandler requestAttributeHandler
                = (RequestAttributeHandler) session.getAttribute(RequestParameter.REQUEST_ATTRIBUTE_HANDLER);
        Map<String, Object> attributes = requestAttributeHandler.getAttributes();
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        String pageNumber = request.getParameter(RequestParameter.PAGE_NUMBER);
        request.setAttribute(RequestParameter.PAGE_NUMBER, pageNumber);
        return (String) session.getAttribute(RequestParameter.CURRENT_PAGE);
    }
}
