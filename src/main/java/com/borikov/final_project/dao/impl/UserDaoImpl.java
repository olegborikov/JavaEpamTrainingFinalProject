package com.borikov.final_project.dao.impl;

import com.borikov.final_project.dao.ColumnName;
import com.borikov.final_project.dao.UserDao;
import com.borikov.final_project.entity.User;
import com.borikov.final_project.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String URL =
            "jdbc:mysql://localhost:3306/jetlogin?useUnicode=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String FIND_USER_BY_LOGIN = "SELECT user_id, login, " +
            "password FROM user WHERE login LIKE ?";

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        ResultSet resultSet = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new DaoException("Driver register error", e);
        }
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // TODO: 02.09.2020 create custom connection pool
             PreparedStatement statement =
                     connection.prepareStatement(FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            Optional<User> userOptional = Optional.empty();
            if (resultSet.next()) {
                User user = createUserFromResultSet(resultSet);
                userOptional = Optional.of(user);
            }
            return userOptional;
        } catch (SQLException e) {
            throw new DaoException("Finding user by login error", e);
        } finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public boolean add(User user) throws DaoException {
        return false;
    }


    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        long userId = resultSet.getLong(ColumnName.USER_ID);
        String login = resultSet.getString(ColumnName.LOGIN);
        String password = resultSet.getString(ColumnName.PASSWORD);
        return new User(userId, login, password);
    }
}
