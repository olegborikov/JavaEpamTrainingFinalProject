package com.borikov.bullfinch.model.service;

import com.borikov.bullfinch.model.entity.User;
import com.borikov.bullfinch.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Add user boolean.
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
     * Edit user boolean.
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
     * Confirm user email boolean.
     *
     * @param login the login
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean confirmUserEmail(String login) throws ServiceException;

    /**
     * Block user boolean.
     *
     * @param login the login
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean blockUser(String login) throws ServiceException;

    /**
     * Unblock user boolean.
     *
     * @param login the login
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean unblockUser(String login) throws ServiceException;

    /**
     * Authorize user optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> authorizeUser(String login, String password) throws ServiceException;

    /**
     * Find all users list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findAllUsers() throws ServiceException;

    /**
     * Find user by login optional.
     *
     * @param login the login
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findUserByLogin(String login) throws ServiceException;

    /**
     * Find users by login substring list.
     *
     * @param loginSubstring the login substring
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findUsersByLoginSubstring(String loginSubstring) throws ServiceException;
}
