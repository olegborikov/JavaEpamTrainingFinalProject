package com.borikov.bullfinch.dao.impl;

import com.borikov.bullfinch.builder.UserBuilder;
import com.borikov.bullfinch.dao.ColumnName;
import com.borikov.bullfinch.dao.UserDao;
import com.borikov.bullfinch.dao.pool.ConnectionPool;
import com.borikov.bullfinch.entity.User;
import com.borikov.bullfinch.entity.UserRole;
import com.borikov.bullfinch.entity.Wallet;
import com.borikov.bullfinch.exception.ConnectionPoolException;
import com.borikov.bullfinch.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private static final String CHECK_EXISTING_BY_LOGIN = "SELECT password " +
            "FROM user_account WHERE BINARY login LIKE ?";
    private static final String CHECK_EXISTING_BY_EMAIL = "SELECT user_account_id " +
            "FROM user_account WHERE email LIKE ?";
    private static final String FIND_BY_LOGIN = "SELECT user_account_id, email, login, " +
            "first_name, second_name, phone_number, is_blocked, is_activated, " +
            "wallet_id, balance, role_name FROM user_account " +
            "INNER JOIN role ON user_account.role_id_fk = role.role_id " +
            "INNER JOIN wallet ON user_account.wallet_id_fk = wallet.wallet_id " +
            "WHERE login LIKE ?";
    private static final String AUTHORIZE = "SELECT login, " +
            "is_blocked, is_activated, role_name FROM user_account " +
            "INNER JOIN role ON user_account.role_id_fk = role.role_id " +
            "WHERE login LIKE ?";
    private static final String ADD = "INSERT INTO user_account (email, login, password," +
            " first_name, second_name, phone_number, is_blocked, " +
            "is_activated, role_id_fk, wallet_id_fk) " +
            "VALUES (?, ?, ?, ?, ?, ?, 0, 0, ?, ?)";
    private static final String UPDATE = "UPDATE user_account  SET email = ?, login = ?, " +
            "first_name = ?, second_name = ?, phone_number = ? WHERE user_account_id = ?";
    private static final String CONFIRM_EMAIL = "UPDATE user_account " +
            "SET is_activated = 1 WHERE login LIKE ?";
    private static final String FIND_ALL = "SELECT login, email, first_name, second_name " +
            "FROM user_account WHERE BINARY login NOT LIKE 'admin'";
    private static final String BLOCK = "UPDATE user_account SET is_blocked = 1 WHERE BINARY login LIKE ?";
    private static final String UNBLOCK = "UPDATE user_account SET is_blocked = 0 WHERE BINARY login LIKE ?";

    @Override
    public Optional<String> checkExistingByLogin(String login) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(CHECK_EXISTING_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            Optional<String> passwordOptional = Optional.empty();
            if (resultSet.next()) {
                String password = resultSet.getString(ColumnName.PASSWORD);
                passwordOptional = Optional.of(password);
            }
            return passwordOptional;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Existing user by login error", e);
        }
    }

    @Override
    public boolean checkExistingByEmail(String email) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(CHECK_EXISTING_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            boolean result = false;
            if (resultSet.next()) {
                result = true;
            }
            return result;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Existing user by email error", e);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            Optional<User> userOptional = Optional.empty();
            if (resultSet.next()) {
                UserBuilder userBuilder = new UserBuilder();
                userBuilder.setUserId(resultSet.getLong(ColumnName.USER_ACCOUNT_ID));
                userBuilder.setLogin(resultSet.getString(ColumnName.LOGIN));
                userBuilder.setEmail(resultSet.getString(ColumnName.EMAIL));
                userBuilder.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
                userBuilder.setSecondName(resultSet.getString(ColumnName.SECOND_NAME));
                userBuilder.setPhoneNumber(resultSet.getString(ColumnName.PHONE_NUMBER));
                userBuilder.setBlocked(resultSet.getInt(ColumnName.IS_BLOCKED) != 0);
                userBuilder.setActivated(resultSet.getInt(ColumnName.IS_ACTIVATED) != 0);
                String roleName = resultSet.getString(ColumnName.ROLE_NAME);
                userBuilder.setUserRole(UserRole.valueOf(roleName.toUpperCase()));
                userBuilder.setWallet(new Wallet(resultSet.getLong(ColumnName.WALLET_ID),
                        resultSet.getDouble(ColumnName.BALANCE)));
                User user = userBuilder.getUser();
                userOptional = Optional.of(user);
            }
            return userOptional;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Finding user by login error", e);
        }
    }

    @Override
    public Optional<User> authorize(String login) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(AUTHORIZE)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            Optional<User> userOptional = Optional.empty();
            if (resultSet.next()) {
                UserBuilder userBuilder = new UserBuilder();
                userBuilder.setLogin(resultSet.getString(ColumnName.LOGIN));
                userBuilder.setBlocked(resultSet.getInt(ColumnName.IS_BLOCKED) != 0);
                userBuilder.setActivated(resultSet.getInt(ColumnName.IS_ACTIVATED) != 0);
                String roleName = resultSet.getString(ColumnName.ROLE_NAME);
                userBuilder.setUserRole(UserRole.valueOf(roleName.toUpperCase()));
                User user = userBuilder.getUser();
                userOptional = Optional.of(user);
            }
            return userOptional;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Finding user by login error", e);
        }
    }

    @Override
    public boolean confirmEmail(String login) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(CONFIRM_EMAIL)) {
            statement.setString(1, login);
            boolean result = statement.executeUpdate() > 0;
            return result;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Confirm email error", e);
        }
    }

    @Override
    public boolean add(User user, String password, Connection connection) throws DaoException {// TODO: 16.09.2020 refactor
        try (PreparedStatement statement =
                     connection.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getLogin());
            statement.setString(3, password);
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getSecondName());
            statement.setString(6, user.getPhoneNumber());
            statement.setLong(7, user.getUserRole().getUserRoleId());
            statement.setLong(8, user.getWallet().getWalletId());
            boolean result = statement.executeUpdate() > 0;
            ResultSet generatedKeysUser = statement.getGeneratedKeys();
            if (generatedKeysUser.next()) {
                user.setUserId(generatedKeysUser.getLong(1));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException("Add user error", e);
        }
    }

    @Override
    public boolean update(User user) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(UPDATE)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getSecondName());
            statement.setString(5, user.getPhoneNumber());
            statement.setLong(6, user.getUserId());
            return statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Update user error", e);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                UserBuilder userBuilder = new UserBuilder();
                userBuilder.setLogin(resultSet.getString(ColumnName.LOGIN));
                userBuilder.setEmail(resultSet.getString(ColumnName.EMAIL));
                userBuilder.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
                userBuilder.setSecondName(resultSet.getString(ColumnName.SECOND_NAME));
                User user = userBuilder.getUser();
                users.add(user);
            }
            return users;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Finding all users error", e);
        }
    }

    @Override
    public boolean block(String login) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(BLOCK)) {
            statement.setString(1, login);
            return statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Block user error", e);
        }
    }

    @Override
    public boolean unblock(String login) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(UNBLOCK)) {
            statement.setString(1, login);
            return statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Unblock user error", e);
        }
    }
}
