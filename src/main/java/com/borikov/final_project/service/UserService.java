package com.borikov.final_project.service;

import com.borikov.final_project.exception.ServiceException;

public interface UserService {
    boolean isUserExists(String login, String password) throws ServiceException;

    boolean addUser(String login, String password) throws ServiceException;
}
