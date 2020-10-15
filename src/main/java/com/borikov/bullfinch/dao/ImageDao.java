package com.borikov.bullfinch.dao;

import com.borikov.bullfinch.entity.Image;
import com.borikov.bullfinch.exception.DaoException;

import java.sql.Connection;

public interface ImageDao {
    boolean add(Image image, Connection connection) throws DaoException;

    boolean remove(long id, Connection connection) throws DaoException;
}
