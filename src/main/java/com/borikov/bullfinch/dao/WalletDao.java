package com.borikov.bullfinch.dao;

import com.borikov.bullfinch.entity.Wallet;
import com.borikov.bullfinch.exception.DaoException;

import java.util.Optional;

public interface WalletDao {
    Optional<Wallet> findById(long id) throws DaoException;

    boolean update(Wallet wallet) throws DaoException;
}
