package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.RequestAttribute;
import com.borikov.bullfinch.controller.RequestAttributeHandler;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.SessionAttribute;
import com.borikov.bullfinch.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * The {@code SwitchLocaleCommand} class represents switch locale command.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class SwitchLocaleCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String locale = request.getParameter(RequestParameter.NEW_LOCALE);
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.CURRENT_LOCALE, locale);
        RequestAttributeHandler requestAttributeHandler
                = (RequestAttributeHandler) session.getAttribute(SessionAttribute.REQUEST_ATTRIBUTE_HANDLER);
        Map<String, Object> attributes = requestAttributeHandler.getAttributes();
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        return (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
    }
}
