package com.borikov.bullfinch.model.dao;

import com.borikov.bullfinch.model.entity.Wallet;
import com.borikov.bullfinch.model.exception.DaoException;

import java.sql.Connection;
import java.util.Optional;

/**
 * The interface Wallet dao.
 */
public interface WalletDao {
    /**
     * Add boolean.
     *
     * @param wallet     the wallet
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(Wallet wallet, Connection connection) throws DaoException;

    /**
     * Update boolean.
     *
     * @param wallet the wallet
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean update(Wallet wallet) throws DaoException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Wallet> findById(long id) throws DaoException;

    /**
     * Find by user login optional.
     *
     * @param userLogin the user login
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Wallet> findByUserLogin(String userLogin) throws DaoException;

    /**
     * Find by order id optional.
     *
     * @param orderId the order id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Wallet> findByOrderId(long orderId) throws DaoException;
}
