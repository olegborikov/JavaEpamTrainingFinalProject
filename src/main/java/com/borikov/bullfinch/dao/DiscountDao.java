package com.borikov.bullfinch.dao;

import com.borikov.bullfinch.entity.Discount;
import com.borikov.bullfinch.exception.DaoException;

import java.util.List;

public interface DiscountDao {
    boolean add(Discount discount) throws DaoException;

    boolean remove(long id) throws DaoException;

    List<Discount> findByUserLogin(String userLogin) throws DaoException;
}
