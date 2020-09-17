package com.borikov.bullfinch.dao;

import com.borikov.bullfinch.exception.DaoException;

import java.util.List;

public interface CommonDao<T> {
    boolean add(T t) throws DaoException;

    boolean remove(T t) throws DaoException;

    boolean update(T t) throws DaoException;

    List<T> findAll() throws DaoException;
}
