package com.borikov.bullfinch.dao;

import com.borikov.bullfinch.entity.User;
import com.borikov.bullfinch.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<String> checkExistingByLogin(String login) throws DaoException;

    boolean checkExistingByEmail(String email) throws DaoException;

    Optional<User> findByLogin(String login) throws DaoException;

    boolean confirmEmail(String login) throws DaoException;

    boolean add(User user, String password) throws DaoException;

    boolean remove(User user) throws DaoException;

    boolean update(User user) throws DaoException;

    List<User> findAll() throws DaoException;
}
