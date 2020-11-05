package com.borikov.bullfinch.model.dao;

import com.borikov.bullfinch.model.entity.User;
import com.borikov.bullfinch.model.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * The {@code UserDao} interface represents user dao.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public interface UserDao {
    /**
     * Add user.
     *
     * @param user       the user
     * @param password   the password
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(User user, String password, Connection connection) throws DaoException;

    /**
     * Update user.
     *
     * @param user the user
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean update(User user) throws DaoException;

    /**
     * Authorize user.
     *
     * @param login the login
     * @return the optional of authorized user
     * @throws DaoException the dao exception
     */
    Optional<User> authorize(String login) throws DaoException;

    /**
     * Confirm user email.
     *
     * @param login the login
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean confirmEmail(String login) throws DaoException;

    /**
     * Block user.
     *
     * @param login the login
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean block(String login) throws DaoException;

    /**
     * Unblock user.
     *
     * @param login the login
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean unblock(String login) throws DaoException;

    /**
     * Check user existing by login.
     *
     * @param login the login
     * @return the optional of found user password
     * @throws DaoException the dao exception
     */
    Optional<String> checkExistingByLogin(String login) throws DaoException;

    /**
     * Check user existing by email.
     *
     * @param email the email
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean checkExistingByEmail(String email) throws DaoException;

    /**
     * Find all users.
     *
     * @return the list of found users
     * @throws DaoException the dao exception
     */
    List<User> findAll() throws DaoException;

    /**
     * Find user by login.
     *
     * @param login the login
     * @return the optional of found user
     * @throws DaoException the dao exception
     */
    Optional<User> findByLogin(String login) throws DaoException;

    /**
     * Find users by login substrings.
     *
     * @param loginSubstring the login substring
     * @return the list of found users
     * @throws DaoException the dao exception
     */
    List<User> findByLoginSubstring(String loginSubstring) throws DaoException;
}
