package com.borikov.bullfinch.controller.command.impl.page;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Browse discount add page command.
 */
public class BrowseDiscountAddPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String userId = request.getParameter(RequestParameter.USER_ID);
        request.setAttribute(RequestParameter.USER_ID, userId);
        return PagePath.DISCOUNT_ADD;
    }
}
