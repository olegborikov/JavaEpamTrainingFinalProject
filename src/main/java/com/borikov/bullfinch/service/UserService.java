package com.borikov.bullfinch.service;

import com.borikov.bullfinch.entity.User;
import com.borikov.bullfinch.exception.ServiceException;

import java.util.Optional;

public interface UserService {
    Optional<User> isUserExists(String login, String password) throws ServiceException;

    boolean addUser(String email, String login, String firstName,
                    String secondName, String phoneNumber, String password,
                    String confirmedPassword) throws ServiceException;

    boolean confirmUserEmail(String login) throws ServiceException;
}
