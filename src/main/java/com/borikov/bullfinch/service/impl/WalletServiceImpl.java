package com.borikov.bullfinch.service.impl;

import com.borikov.bullfinch.dao.WalletDao;
import com.borikov.bullfinch.dao.impl.WalletDaoImpl;
import com.borikov.bullfinch.entity.Wallet;
import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.service.WalletService;
import com.borikov.bullfinch.validator.impl.WalletValidator;

import java.util.Optional;

public class WalletServiceImpl implements WalletService {
    private final WalletDao walletDao = new WalletDaoImpl();

    @Override
    public boolean enrichBalance(String walletId, String enrichAmount)
            throws ServiceException {
        try {
            boolean result = false;
            WalletValidator walletValidator = new WalletValidator();
            if (walletValidator.isIdCorrect(walletId)
                    && walletValidator.isEnrichAmountCorrect(enrichAmount)) {
                long id = Long.parseLong(walletId);
                double amount = Double.parseDouble(enrichAmount);
                Optional<Wallet> walletOptional = walletDao.findById(id);
                if (walletOptional.isPresent()) {
                    Wallet wallet = walletOptional.get();
                    double newBalance = wallet.getBalance() + amount;
                    if (walletValidator.isBalanceCorrect(newBalance)) {
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
}


