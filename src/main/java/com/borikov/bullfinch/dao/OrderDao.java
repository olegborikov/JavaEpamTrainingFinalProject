package com.borikov.bullfinch.dao;

import com.borikov.bullfinch.entity.Order;
import com.borikov.bullfinch.exception.DaoException;

import java.util.List;

public interface OrderDao {
    boolean add(Order order) throws DaoException;

    List<Order> findByUserLogin(String userLogin) throws DaoException;
}
