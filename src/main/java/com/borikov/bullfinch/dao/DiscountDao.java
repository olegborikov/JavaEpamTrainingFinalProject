package com.borikov.bullfinch.dao;

import com.borikov.bullfinch.entity.Discount;
import com.borikov.bullfinch.exception.DaoException;

public interface DiscountDao {
    boolean add(Discount discount) throws DaoException;

    boolean remove(long id) throws DaoException;
}
