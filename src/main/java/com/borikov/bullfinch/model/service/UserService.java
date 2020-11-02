package com.borikov.bullfinch.model.service;

import com.borikov.bullfinch.model.entity.User;
import com.borikov.bullfinch.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean addUser(String email, String login, String firstName, String secondName, String phoneNumber,
                    String password, String confirmedPassword) throws ServiceException;

    boolean editUser(String id, String email, String login, String firstName, String secondName,
                     String phoneNumber) throws ServiceException;

    boolean confirmUserEmail(String login) throws ServiceException;

    boolean blockUser(String login) throws ServiceException;

    boolean unblockUser(String login) throws ServiceException;

    Optional<User> authorizeUser(String login, String password) throws ServiceException;

    List<User> findAllUsers() throws ServiceException;

    Optional<User> findUserByLogin(String login) throws ServiceException;

    List<User> findUsersByLoginSubstring(String loginSubstring) throws ServiceException;
}
