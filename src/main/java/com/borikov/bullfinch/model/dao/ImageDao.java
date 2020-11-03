package com.borikov.bullfinch.model.dao;

import com.borikov.bullfinch.model.entity.Image;
import com.borikov.bullfinch.model.exception.DaoException;

import java.sql.Connection;

/**
 * The interface Image dao.
 */
public interface ImageDao {
    /**
     * Add boolean.
     *
     * @param image      the image
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(Image image, Connection connection) throws DaoException;

    /**
     * Remove boolean.
     *
     * @param id         the id
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean remove(long id, Connection connection) throws DaoException;
}
