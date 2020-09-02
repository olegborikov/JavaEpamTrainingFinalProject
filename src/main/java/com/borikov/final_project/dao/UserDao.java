package com.borikov.final_project.dao;

import com.borikov.final_project.entity.User;
import com.borikov.final_project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public interface UserDao {
    Logger LOGGER = LogManager.getLogger();
    Optional<User> findByLogin(String login) throws DaoException;

    default void closeResultSet(ResultSet resultSet) { // TODO: 02.09.2020 move to common dao
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while closing resultSet");
        }
    }
}
