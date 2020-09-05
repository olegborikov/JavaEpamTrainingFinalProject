package com.borikov.bullfinch.dao;

import com.borikov.bullfinch.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CommonDao<T> {
    Logger LOGGER = LogManager.getLogger();

    boolean add(T t) throws DaoException;

    boolean remove(T t) throws DaoException;

    boolean update(T t) throws DaoException;

    List<T> findAll() throws DaoException;

    default void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while closing resultSet");
        }
    }
}
