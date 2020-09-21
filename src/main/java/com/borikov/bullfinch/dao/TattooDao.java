package com.borikov.bullfinch.dao;

import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.exception.DaoException;

import java.util.List;

public interface TattooDao {
    List<Tattoo> findAll() throws DaoException;
}
