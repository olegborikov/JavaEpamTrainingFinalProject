package com.borikov.bullfinch.dao;

import com.borikov.bullfinch.entity.Wallet;
import com.borikov.bullfinch.exception.DaoException;

public interface WalletDao {
    boolean update(Wallet wallet) throws DaoException;
}
