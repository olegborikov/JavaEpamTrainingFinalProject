package com.borikov.bullfinch.dao;

import com.borikov.bullfinch.entity.Order;
import com.borikov.bullfinch.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    boolean add(Order order) throws DaoException;

    List<Order> findByUserLogin(String userLogin) throws DaoException;

    Optional<Order> findById(long id) throws DaoException;

    boolean remove(long id) throws DaoException;
}
