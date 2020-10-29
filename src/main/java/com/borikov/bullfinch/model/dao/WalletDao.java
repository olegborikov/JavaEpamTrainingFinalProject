package com.borikov.bullfinch.model.dao;

import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.model.entity.Wallet;

import java.sql.Connection;
import java.util.Optional;

public interface WalletDao {
    boolean add(Wallet wallet, Connection connection) throws DaoException;

    boolean update(Wallet wallet) throws DaoException;

    Optional<Wallet> findById(long id) throws DaoException;

    Optional<Wallet> findByUserLogin(String userLogin) throws DaoException;

    Optional<Wallet> findByOrderId(long orderId) throws DaoException;
}
