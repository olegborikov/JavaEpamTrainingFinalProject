package com.borikov.bullfinch.model.service;

import com.borikov.bullfinch.model.entity.User;
import com.borikov.bullfinch.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The {@code UserService} interface represents user service.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public interface UserService {
    /**
     * Add user.
     *
     * @param email             the email
     * @param login             the login
     * @param firstName         the first name
     * @param secondName        the second name
     * @param phoneNumber       the phone number
     * @param password          the password
     * @param confirmedPassword the confirmed password
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addUser(String email, String login, String firstName, String secondName, String phoneNumber,
                    String password, String confirmedPassword) throws ServiceException;

    /**
     * Edit user.
     *
     * @param id          the id
     * @param email       the email
     * @param login       the login
     * @param firstName   the first name
     * @param secondName  the second name
     * @param phoneNumber the phone number
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean editUser(String id, String email, String login, String firstName, String secondName,
                     String phoneNumber) throws ServiceException;

    /**
     * Confirm user email.
     *
     * @param login the login
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean confirmUserEmail(String login) throws ServiceException;

    /**
     * Block user.
     *
     * @param login the login
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean blockUser(String login) throws ServiceException;

    /**
     * Unblock user.
     *
     * @param login the login
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean unblockUser(String login) throws ServiceException;

    /**
     * Authorize user.
     *
     * @param login    the login
     * @param password the password
     * @return the optional of authorized user
     * @throws ServiceException the service exception
     */
    Optional<User> authorizeUser(String login, String password) throws ServiceException;

    /**
     * Find all users.
     *
     * @return the list of found users
     * @throws ServiceException the service exception
     */
    List<User> findAllUsers() throws ServiceException;

    /**
     * Find user by login.
     *
     * @param login the login
     * @return the optional of found user
     * @throws ServiceException the service exception
     */
    Optional<User> findUserByLogin(String login) throws ServiceException;

    /**
     * Find users by login substring.
     *
     * @param loginSubstring the login substring
     * @return the list of found users
     * @throws ServiceException the service exception
     */
    List<User> findUsersByLoginSubstring(String loginSubstring) throws ServiceException;
}
