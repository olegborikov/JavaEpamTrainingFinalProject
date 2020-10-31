package com.borikov.bullfinch.model.dao;

import com.borikov.bullfinch.model.exception.DaoException;
import com.borikov.bullfinch.model.entity.Image;

import java.sql.Connection;

public interface ImageDao {
    boolean add(Image image, Connection connection) throws DaoException;

    boolean remove(long id, Connection connection) throws DaoException;
}
