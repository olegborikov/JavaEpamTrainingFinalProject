package com.borikov.bullfinch.model.service;

import com.borikov.bullfinch.model.exception.ServiceException;

/**
 * The {@code WalletService} interface represents wallet service.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public interface WalletService {
    /**
     * Enrich wallet balance.
     *
     * @param walletId     the wallet id
     * @param enrichAmount the enrich amount
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean enrichBalance(String walletId, String enrichAmount) throws ServiceException;

    /**
     * Check wallet balance size.
     *
     * @param userLogin the user login
     * @param price     the price
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean checkBalanceSize(String userLogin, String price) throws ServiceException;

    /**
     * Check wallet balance size.
     *
     * @param orderId the order id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean checkBalanceSize(String orderId) throws ServiceException;
}
