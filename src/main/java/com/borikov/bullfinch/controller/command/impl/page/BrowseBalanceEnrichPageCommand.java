package com.borikov.bullfinch.controller.command.impl.page;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Browse balance enrich page command.
 */
public class BrowseBalanceEnrichPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String walletId = request.getParameter(RequestParameter.WALLET_ID);
        request.setAttribute(RequestParameter.WALLET_ID, walletId);
        return PagePath.BALANCE_ENRICH;
    }
}
