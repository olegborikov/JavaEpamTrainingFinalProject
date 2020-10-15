package com.borikov.bullfinch.dao;

import com.borikov.bullfinch.entity.Wallet;
import com.borikov.bullfinch.exception.DaoException;

import java.sql.Connection;
import java.util.Optional;

public interface WalletDao {
    boolean add(Wallet wallet, Connection connection) throws DaoException;

    Optional<Wallet> findById(long id) throws DaoException;

    boolean update(Wallet wallet) throws DaoException;
}
