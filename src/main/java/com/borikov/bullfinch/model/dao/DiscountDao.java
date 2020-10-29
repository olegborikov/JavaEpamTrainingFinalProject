package com.borikov.bullfinch.model.dao;

import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.model.entity.Discount;

import java.util.List;

public interface DiscountDao {
    boolean add(Discount discount) throws DaoException;

    boolean remove(long id) throws DaoException;

    List<Discount> findByUserLogin(String userLogin) throws DaoException;
}
