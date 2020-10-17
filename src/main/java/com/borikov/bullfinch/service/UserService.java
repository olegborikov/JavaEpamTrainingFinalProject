package com.borikov.bullfinch.service;

import com.borikov.bullfinch.entity.User;
import com.borikov.bullfinch.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean addUser(String email, String login, String firstName,
                    String secondName, String phoneNumber, String password,
                    String confirmedPassword) throws ServiceException;

    boolean editUser(String id, String email, String login,
                     String firstName, String secondName,
                     String phoneNumber) throws ServiceException;

    boolean confirmUserEmail(String login) throws ServiceException;

    boolean blockUser(String login) throws ServiceException;

    boolean unblockUser(String login) throws ServiceException;

    Optional<User> isUserExists(String login, String password)
            throws ServiceException;

    List<User> findAllUsers() throws ServiceException;

    Optional<User> findUserByLogin(String name) throws ServiceException;
}
