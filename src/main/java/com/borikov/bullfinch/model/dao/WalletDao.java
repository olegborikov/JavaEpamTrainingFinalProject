package com.borikov.bullfinch.model.dao;

import com.borikov.bullfinch.model.entity.Wallet;
import com.borikov.bullfinch.model.exception.DaoException;

import java.sql.Connection;
import java.util.Optional;

/**
 * The {@code WalletDao} interface represents wallet dao.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public interface WalletDao {
    /**
     * Add wallet.
     *
     * @param wallet     the wallet
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(Wallet wallet, Connection connection) throws DaoException;

    /**
     * Update wallet.
     *
     * @param wallet the wallet
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean update(Wallet wallet) throws DaoException;

    /**
     * Find wallet by id.
     *
     * @param id the id
     * @return the optional of found wallet
     * @throws DaoException the dao exception
     */
    Optional<Wallet> findById(long id) throws DaoException;

    /**
     * Find wallet by user login.
     *
     * @param userLogin the user login
     * @return the optional of found wallet
     * @throws DaoException the dao exception
     */
    Optional<Wallet> findByUserLogin(String userLogin) throws DaoException;

    /**
     * Find wallet by order id.
     *
     * @param orderId the order id
     * @return the optional of found wallet
     * @throws DaoException the dao exception
     */
    Optional<Wallet> findByOrderId(long orderId) throws DaoException;
}
