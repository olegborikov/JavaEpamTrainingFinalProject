package com.borikov.bullfinch.model.dao;

import com.borikov.bullfinch.model.entity.User;
import com.borikov.bullfinch.model.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    boolean add(User user, String password, Connection connection) throws DaoException;

    boolean update(User user) throws DaoException;

    Optional<User> authorize(String login) throws DaoException;

    boolean confirmEmail(String login) throws DaoException;

    boolean block(String login) throws DaoException;

    boolean unblock(String login) throws DaoException;

    Optional<String> checkExistingByLogin(String login) throws DaoException;

    boolean checkExistingByEmail(String email) throws DaoException;

    List<User> findAll() throws DaoException;

    Optional<User> findByLogin(String login) throws DaoException;

    List<User> findByLoginSubstring(String loginSubstring) throws DaoException;
}
