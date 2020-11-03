package com.borikov.bullfinch.model.dao;

import com.borikov.bullfinch.model.entity.User;
import com.borikov.bullfinch.model.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * The interface User dao.
 */
public interface UserDao {
    /**
     * Add boolean.
     *
     * @param user       the user
     * @param password   the password
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(User user, String password, Connection connection) throws DaoException;

    /**
     * Update boolean.
     *
     * @param user the user
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean update(User user) throws DaoException;

    /**
     * Authorize optional.
     *
     * @param login the login
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> authorize(String login) throws DaoException;

    /**
     * Confirm email boolean.
     *
     * @param login the login
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean confirmEmail(String login) throws DaoException;

    /**
     * Block boolean.
     *
     * @param login the login
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean block(String login) throws DaoException;

    /**
     * Unblock boolean.
     *
     * @param login the login
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean unblock(String login) throws DaoException;

    /**
     * Check existing by login optional.
     *
     * @param login the login
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<String> checkExistingByLogin(String login) throws DaoException;

    /**
     * Check existing by email boolean.
     *
     * @param email the email
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean checkExistingByEmail(String email) throws DaoException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findAll() throws DaoException;

    /**
     * Find by login optional.
     *
     * @param login the login
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findByLogin(String login) throws DaoException;

    /**
     * Find by login substring list.
     *
     * @param loginSubstring the login substring
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findByLoginSubstring(String loginSubstring) throws DaoException;
}
