package com.borikov.bullfinch.service;

import com.borikov.bullfinch.exception.ServiceException;

public interface UserService {
    boolean isUserExists(String login, String password) throws ServiceException;

    boolean addUser(String login, String password) throws ServiceException;
}
