package com.borikov.bullfinch.dao.impl;

import com.borikov.bullfinch.dao.ColumnName;
import com.borikov.bullfinch.dao.UserDao;
import com.borikov.bullfinch.dao.pool.ConnectionPool;
import com.borikov.bullfinch.entity.User;
import com.borikov.bullfinch.entity.UserRole;
import com.borikov.bullfinch.exception.ConnectionPoolException;
import com.borikov.bullfinch.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private static final String FIND_USER_BY_LOGIN = "SELECT user_account_id, login, " +
            "password, role_name FROM user_account " +
            "INNER JOIN role ON user_account.role_id_fk = role.role_id " +
            "WHERE login LIKE ?";
    private static final String ADD_USER = "INSERT INTO user_account (email, login, password," +
            " first_name, second_name, phone_number, is_blocked, " +
            "is_activated, role_id_fk, wallet_id_fk, rating_id_fk) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);\n";
    private static final String ADD_WALLET = "INSERT INTO wallet (balance)" +
            "VALUES (?);";

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        ResultSet resultSet = null;
        try (Connection connection = connectionPool.getConnection();
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
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Finding user by login error", e);
        } finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public boolean add(User user) throws DaoException {// TODO: 16.09.2020 refactor
        ResultSet generatedKeysWallet = null;
        ResultSet generatedKeysUser = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statementWallet =
                     connection.prepareStatement(ADD_WALLET, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statementUser =
                     connection.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS)) {
            statementWallet.setDouble(1, user.getWallet().getBalance());
            statementWallet.executeUpdate();
            generatedKeysWallet = statementWallet.getGeneratedKeys();
            if (generatedKeysWallet.next()) {
                user.getWallet().setWalletId(generatedKeysWallet.getLong(1));
            }
            statementUser.setString(1, user.getEmail());
            statementUser.setString(2, user.getLogin());
            statementUser.setString(3, user.getPassword());
            statementUser.setString(4, user.getFirstName());
            statementUser.setString(5, user.getSecondName());
            statementUser.setString(6, user.getPhoneNumber());
            statementUser.setInt(7, user.isBlocked() ? 1 : 0);
            statementUser.setInt(8, user.isActivated() ? 1 : 0);
            statementUser.setLong(9, user.getUserRole().getUserRoleId());
            statementUser.setLong(10, user.getWallet().getWalletId());
            statementUser.setLong(11, user.getUserRating().getUserRatingId());
            boolean result = statementUser.executeUpdate() > 0;
            generatedKeysUser = statementUser.getGeneratedKeys();
            if (generatedKeysUser.next()) {
                user.setUserId(generatedKeysUser.getLong(1));
            }
            return result;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Finding user by login error", e);
        } finally {
            closeResultSet(generatedKeysWallet);
            closeResultSet(generatedKeysUser);
        }
    }

    @Override
    public boolean remove(User user) throws DaoException {
        return false;
    }

    @Override
    public boolean update(User user) throws DaoException {
        return false;
    }

    @Override
    public List<User> findAll() throws DaoException {
        return null;
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        long userId = resultSet.getLong(ColumnName.USER_ACCOUNT_ID);
        String login = resultSet.getString(ColumnName.LOGIN);
        String password = resultSet.getString(ColumnName.PASSWORD);
        int rolePosition = resultSet.getInt(ColumnName.ROLE_NAME);
        UserRole userRole = UserRole.values()[rolePosition];
        return new User(userId, login, password, userRole);
    }
}
