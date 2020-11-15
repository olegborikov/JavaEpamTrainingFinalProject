package com.borikov.bullfinch.model.dao.impl;

import com.borikov.bullfinch.model.builder.UserBuilder;
import com.borikov.bullfinch.model.dao.ColumnName;
import com.borikov.bullfinch.model.dao.UserDao;
import com.borikov.bullfinch.model.entity.User;
import com.borikov.bullfinch.model.entity.UserRole;
import com.borikov.bullfinch.model.entity.Wallet;
import com.borikov.bullfinch.model.exception.DaoException;
import com.borikov.bullfinch.model.pool.ConnectionPool;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.a.result.NativeResultset;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The {@code UserDaoImpl} class represents user dao implementation.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class UserDaoImpl implements UserDao {
    private static final UserDaoImpl instance = new UserDaoImpl();
    private static final String ADD = "INSERT INTO user_account (email, login, password, first_name, second_name, "
            + "phone_number, is_blocked, is_activated, role_id_fk, wallet_id_fk) "
            + "VALUES (?, ?, ?, ?, ?, ?, 0, 0, ?, ?)";
    private static final String UPDATE = "UPDATE user_account SET email = ?, login = ?, first_name = ?, "
            + "second_name = ?, phone_number = ? WHERE user_account_id = ?";
    private static final String AUTHORIZE = "SELECT login, is_blocked, is_activated, role_name FROM user_account "
            + "INNER JOIN role ON user_account.role_id_fk = role.role_id WHERE BINARY login LIKE ?";
    private static final String CONFIRM_EMAIL = "UPDATE user_account SET is_activated = 1 WHERE BINARY login LIKE ?";
    private static final String BLOCK = "UPDATE user_account SET is_blocked = 1 WHERE BINARY login LIKE ?";
    private static final String UNBLOCK = "UPDATE user_account SET is_blocked = 0 WHERE BINARY login LIKE ?";
    private static final String CHECK_EXISTING_BY_LOGIN = "SELECT password FROM user_account "
            + "WHERE BINARY login LIKE ?";
    private static final String CHECK_EXISTING_BY_EMAIL = "SELECT user_account_id FROM user_account "
            + "WHERE email LIKE ?";
    private static final String FIND_ALL = "SELECT login, email, first_name, second_name FROM user_account "
            + "WHERE BINARY login NOT LIKE 'admin'";
    private static final String FIND_BY_LOGIN = "SELECT user_account_id, email, login, first_name, second_name, "
            + "phone_number, is_blocked, is_activated, wallet_id, balance, role_name "
            + "FROM user_account INNER JOIN role ON user_account.role_id_fk = role.role_id "
            + "INNER JOIN wallet ON user_account.wallet_id_fk = wallet.wallet_id WHERE BINARY login LIKE ?";
    private static final String FIND_BY_LOGIN_SUBSTRING = "SELECT login, email, first_name, second_name "
            + "FROM user_account WHERE BINARY login NOT LIKE 'admin' AND BINARY login LIKE ?";
    private static final String PERCENT = "%";

    private UserDaoImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean add(User user, String password, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getLogin());
            statement.setString(3, password);
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getSecondName());
            statement.setString(6, user.getPhoneNumber());
            statement.setLong(7, user.getUserRole().getUserRoleId());
            statement.setLong(8, user.getWallet().getWalletId());
            boolean isAdded = statement.executeUpdate() > 0;
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setUserId(generatedKeys.getLong(1));
            }
            return isAdded;
        } catch (SQLException e) {
            throw new DaoException("Error while adding user: " + user, e);
        }
    }

    @Override
    public boolean update(User user) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getSecondName());
            statement.setString(5, user.getPhoneNumber());
            statement.setLong(6, user.getUserId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while updating user: " + user, e);
        }
    }

    @Override
    public Optional<User> authorize(String login) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(AUTHORIZE)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            Optional<User> userOptional = Optional.empty();
            if (resultSet.next()) {
                userOptional = Optional.of(createUserFromResultSet(resultSet));
            }
            return userOptional;
        } catch (SQLException e) {
            throw new DaoException("Error while authorize user: login = " + login, e);
        }
    }

    @Override
    public boolean confirmEmail(String login) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(CONFIRM_EMAIL)) {
            statement.setString(1, login);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while confirming user email: login = " + login, e);
        }
    }

    @Override
    public boolean block(String login) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(BLOCK)) {
            statement.setString(1, login);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while blocking user: login = " + login, e);
        }
    }

    @Override
    public boolean unblock(String login) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UNBLOCK)) {
            statement.setString(1, login);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while unblocking user: login = " + login, e);
        }
    }

    @Override
    public Optional<String> checkExistingByLogin(String login) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_EXISTING_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            Optional<String> passwordOptional = Optional.empty();
            if (resultSet.next()) {
                String password = resultSet.getString(ColumnName.PASSWORD);
                passwordOptional = Optional.of(password);
            }
            return passwordOptional;
        } catch (SQLException e) {
            throw new DaoException("Error while checking user for existing by login: login = " + login, e);
        }
    }

    @Override
    public boolean checkExistingByEmail(String email) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_EXISTING_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new DaoException("Error while checking user for existing by email: email = " + email, e);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(createUserFromResultSet(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new DaoException("Error while finding all users", e);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            Optional<User> userOptional = Optional.empty();
            if (resultSet.next()) {
                userOptional = Optional.of(createUserFromResultSet(resultSet));
            }
            return userOptional;
        } catch (SQLException e) {
            throw new DaoException("Error while finding user by login: " + login, e);
        }
    }

    @Override
    public List<User> findByLoginSubstring(String loginSubstring) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_SUBSTRING)) {
            statement.setString(1, PERCENT + loginSubstring + PERCENT);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(createUserFromResultSet(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new DaoException("Error while finding users by login substring: " + loginSubstring, e);
        }
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        ColumnDefinition columnDefinition = ((NativeResultset) resultSet).getColumnDefinition();
        UserBuilder userBuilder = new UserBuilder();
        String login = resultSet.getString(ColumnName.LOGIN);
        userBuilder.setLogin(login);
        if (columnDefinition.findColumn(ColumnName.USER_ACCOUNT_ID, true, 1) != -1) {
            long userId = resultSet.getLong(ColumnName.USER_ACCOUNT_ID);
            userBuilder.setUserId(userId);
        }
        if (columnDefinition.findColumn(ColumnName.EMAIL, true, 1) != -1) {
            String email = resultSet.getString(ColumnName.EMAIL);
            userBuilder.setEmail(email);
        }
        if (columnDefinition.findColumn(ColumnName.FIRST_NAME, true, 1) != -1) {
            String firstName = resultSet.getString(ColumnName.FIRST_NAME);
            userBuilder.setFirstName(firstName);
        }
        if (columnDefinition.findColumn(ColumnName.SECOND_NAME, true, 1) != -1) {
            String secondName = resultSet.getString(ColumnName.SECOND_NAME);
            userBuilder.setSecondName(secondName);
        }
        if (columnDefinition.findColumn(ColumnName.PHONE_NUMBER, true, 1) != -1) {
            String phoneNumber = resultSet.getString(ColumnName.PHONE_NUMBER);
            userBuilder.setPhoneNumber(phoneNumber);
        }
        if (columnDefinition.findColumn(ColumnName.IS_BLOCKED, true, 1) != -1) {
            boolean isBlocked = resultSet.getInt(ColumnName.IS_BLOCKED) != 0;
            userBuilder.setBlocked(isBlocked);
        }
        if (columnDefinition.findColumn(ColumnName.IS_ACTIVATED, true, 1) != -1) {
            boolean isActivated = resultSet.getInt(ColumnName.IS_ACTIVATED) != 0;
            userBuilder.setActivated(isActivated);
        }
        if (columnDefinition.findColumn(ColumnName.ROLE_NAME, true, 1) != -1) {
            String roleName = resultSet.getString(ColumnName.ROLE_NAME);
            userBuilder.setUserRole(UserRole.valueOf(roleName.toUpperCase()));
        }
        if (columnDefinition.findColumn(ColumnName.WALLET_ID, true, 1) != -1
                && columnDefinition.findColumn(ColumnName.BALANCE, true, 1) != -1) {
            long walletId = resultSet.getLong(ColumnName.WALLET_ID);
            double balance = resultSet.getDouble(ColumnName.BALANCE);
            userBuilder.setWallet(new Wallet(walletId, balance));
        }
        return userBuilder.buildUser();
    }
}
