package com.borikov.bullfinch.service;

import com.borikov.bullfinch.entity.User;
import com.borikov.bullfinch.exception.ServiceException;

import java.util.Optional;

public interface UserService {
    Optional<User> isUserExists(String login, String password) throws ServiceException;

    boolean addUser(String login, String password) throws ServiceException;
}
