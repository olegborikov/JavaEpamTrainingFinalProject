package com.borikov.bullfinch.service;

import com.borikov.bullfinch.exception.ServiceException;

public interface WalletService {
    boolean enrichBalance(String walletId, String enrichAmount) throws ServiceException;

    boolean checkBalanceSize(String userLogin, String price) throws ServiceException;

    boolean checkBalanceSize(String orderId) throws ServiceException;
}
