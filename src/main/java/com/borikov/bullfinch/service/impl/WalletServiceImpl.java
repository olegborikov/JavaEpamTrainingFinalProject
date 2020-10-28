package com.borikov.bullfinch.service.impl;

import com.borikov.bullfinch.dao.OrderDao;
import com.borikov.bullfinch.dao.WalletDao;
import com.borikov.bullfinch.dao.impl.OrderDaoImpl;
import com.borikov.bullfinch.dao.impl.WalletDaoImpl;
import com.borikov.bullfinch.entity.Order;
import com.borikov.bullfinch.entity.Wallet;
import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.service.WalletService;
import com.borikov.bullfinch.validator.OrderValidator;
import com.borikov.bullfinch.validator.UserValidator;
import com.borikov.bullfinch.validator.WalletValidator;

import java.util.Optional;

public class WalletServiceImpl implements WalletService {
    private final WalletDao walletDao = WalletDaoImpl.getInstance();
    private final OrderDao orderDao = OrderDaoImpl.getInstance();

    @Override
    public boolean enrichBalance(String walletId, String enrichAmount) throws ServiceException {
        try {
            boolean result = false;
            if (WalletValidator.isIdCorrect(walletId) && WalletValidator.isEnrichAmountCorrect(enrichAmount)) {
                long id = Long.parseLong(walletId);
                double amount = Double.parseDouble(enrichAmount);
                Optional<Wallet> walletOptional = walletDao.findById(id);
                if (walletOptional.isPresent()) {
                    Wallet wallet = walletOptional.get();
                    double newBalance = wallet.getBalance() + amount;
                    if (WalletValidator.isBalanceCorrect(newBalance)) {
                        wallet.setBalance(newBalance);
                        result = walletDao.update(wallet);
                    }
                }
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Error while enrich balance", e);
        }
    }

    @Override
    public boolean checkBalanceSize(String userLogin, String price) throws ServiceException {
        try {
            boolean result = false;
            if (UserValidator.isLoginCorrect(userLogin) && OrderValidator.isPriceCorrect(price)) {
                double orderPrice = Double.parseDouble(price);
                Optional<Wallet> walletOptional = walletDao.findByUserLogin(userLogin);
                if (walletOptional.isPresent()) {
                    Wallet wallet = walletOptional.get();
                    result = wallet.getBalance() >= orderPrice;
                }
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Error while enrich balance", e);
        }
    }

    @Override
    public boolean checkBalanceSize(String orderId) throws ServiceException {
        try {
            boolean result = false;
            if (OrderValidator.isIdCorrect(orderId)) {
                long id = Long.parseLong(orderId);
                Optional<Wallet> walletOptional = walletDao.findByOrderId(id);
                Optional<Order> orderOptional = orderDao.findById(id);
                if (walletOptional.isPresent() && orderOptional.isPresent()) {
                    Wallet wallet = walletOptional.get();
                    Order order = orderOptional.get();
                    result = wallet.getBalance() >= order.getPrice();
                }
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Error while enrich balance", e);
        }
    }
}


