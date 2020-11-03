package com.borikov.bullfinch.model.service;

import com.borikov.bullfinch.model.exception.ServiceException;

/**
 * The interface Wallet service.
 */
public interface WalletService {
    /**
     * Enrich balance boolean.
     *
     * @param walletId     the wallet id
     * @param enrichAmount the enrich amount
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean enrichBalance(String walletId, String enrichAmount) throws ServiceException;

    /**
     * Check balance size boolean.
     *
     * @param userLogin the user login
     * @param price     the price
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean checkBalanceSize(String userLogin, String price) throws ServiceException;

    /**
     * Check balance size boolean.
     *
     * @param orderId the order id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean checkBalanceSize(String orderId) throws ServiceException;
}
