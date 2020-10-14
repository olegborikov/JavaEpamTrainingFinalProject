package com.borikov.bullfinch.controller.command.impl;

import com.borikov.bullfinch.controller.PagePath;
import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.controller.command.Command;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.service.WalletService;
import com.borikov.bullfinch.service.impl.WalletServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class EnrichBalanceCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final WalletService walletService = new WalletServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String walletId = request.getParameter(RequestParameter.WALLET_ID);
        String enrichAmount = request.getParameter(RequestParameter.ENRICH_AMOUNT);
        try {
            if (walletService.enrichBalance(walletId, enrichAmount)) {
                request.setAttribute(RequestParameter.BALANCE_ENRICH_CONFIRM_MESSAGE, true);
            }else {
                request.setAttribute(RequestParameter.BALANCE_ENRICH_ERROR_MESSAGE, true);
            }
            page = PagePath.MESSAGE;
        }catch (ServiceException e){
            LOGGER.log(Level.ERROR, "Error while enrich balance", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR;
        }
        return page;
    }
}
