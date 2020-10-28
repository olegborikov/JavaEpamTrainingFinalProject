package com.borikov.bullfinch.dao.impl;

import com.borikov.bullfinch.dao.ColumnName;
import com.borikov.bullfinch.dao.WalletDao;
import com.borikov.bullfinch.dao.pool.ConnectionPool;
import com.borikov.bullfinch.entity.Wallet;
import com.borikov.bullfinch.exception.ConnectionPoolException;
import com.borikov.bullfinch.exception.DaoException;

import java.sql.*;
import java.util.Optional;

public class WalletDaoImpl implements WalletDao {
    private static final WalletDaoImpl INSTANCE = new WalletDaoImpl();
    private static final String ADD = "INSERT INTO wallet (balance) VALUES (0)";
    private static final String UPDATE = "UPDATE wallet SET balance = ? WHERE wallet_id = ?";
    private static final String FIND_BY_ID = "SELECT wallet_id, balance FROM wallet WHERE wallet_id = ?";
    private static final String FIND_BY_USER_LOGIN = "SELECT wallet_id, balance FROM wallet INNER JOIN user_account "
            + "ON wallet.wallet_id = user_account.wallet_id_fk WHERE BINARY login LIKE ?";
    private static final String FIND_BY_ORDER_ID = "SELECT wallet_id, balance FROM wallet INNER JOIN user_account "
            + "ON wallet.wallet_id = user_account.wallet_id_fk INNER JOIN tattoo_order "
            + "ON user_account.user_account_id = tattoo_order.user_account_id_fk WHERE tattoo_order_id = ?";

    private WalletDaoImpl() {
    }

    public static WalletDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(Wallet wallet, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS)) {
            boolean result = statement.executeUpdate() > 0;
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                wallet.setWalletId(generatedKeys.getLong(1));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException("Error while adding wallet: " + wallet, e);
        }
    }

    @Override
    public boolean update(Wallet wallet) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setDouble(1, wallet.getBalance());
            statement.setLong(2, wallet.getWalletId());
            return statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while updating wallet: " + wallet, e);
        }
    }

    @Override
    public Optional<Wallet> findById(long id) throws DaoException {
        Optional<Wallet> walletOptional = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                walletOptional = Optional.of(createWalletFromResultSet(resultSet));
            }
            return walletOptional;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding wallets by id:" + id, e);
        }
    }

    @Override
    public Optional<Wallet> findByUserLogin(String userLogin) throws DaoException {
        Optional<Wallet> walletOptional = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_LOGIN)) {
            statement.setString(1, userLogin);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                walletOptional = Optional.of(createWalletFromResultSet(resultSet));
            }
            return walletOptional;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding wallets by user login:" + userLogin, e);
        }
    }

    @Override
    public Optional<Wallet> findByOrderId(long orderId) throws DaoException {
        Optional<Wallet> walletOptional = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ORDER_ID)) {
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                walletOptional = Optional.of(createWalletFromResultSet(resultSet));
            }
            return walletOptional;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding wallets by order id:" + orderId, e);
        }
    }

    private Wallet createWalletFromResultSet(ResultSet resultSet)
            throws SQLException {
        long id = resultSet.getLong(ColumnName.WALLET_ID);
        double balance = resultSet.getDouble(ColumnName.BALANCE);
        return new Wallet(id, balance);
    }
}
