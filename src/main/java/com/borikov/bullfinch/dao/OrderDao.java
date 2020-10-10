package com.borikov.bullfinch.dao;

import com.borikov.bullfinch.entity.Order;
import com.borikov.bullfinch.exception.DaoException;

public interface OrderDao {
    boolean add(Order order) throws DaoException;
}
