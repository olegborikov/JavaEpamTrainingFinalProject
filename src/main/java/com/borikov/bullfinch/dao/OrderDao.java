package com.borikov.bullfinch.dao;

import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.exception.DaoException;

public interface OrderDao {
    boolean add(Tattoo tattoo) throws DaoException;
}
