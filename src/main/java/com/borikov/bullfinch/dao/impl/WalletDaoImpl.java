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
    private static final ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private static final String ADD = "INSERT INTO wallet (balance)" +
            "VALUES (0)";
    private static final String FIND_BY_ID = "SELECT wallet_id, balance " +
            "FROM wallet WHERE wallet_id = ?";
    private static final String UPDATE = "UPDATE wallet SET balance = ? WHERE wallet_id = ?";

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
            throw new DaoException("Adding image error", e);
        }
    }

    @Override
    public Optional<Wallet> findById(long id) throws DaoException {
        Optional<Wallet> walletOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Wallet wallet = new Wallet(resultSet.getLong(ColumnName.WALLET_ID),
                        resultSet.getDouble(ColumnName.BALANCE));
                walletOptional = Optional.of(wallet);
            }
            return walletOptional;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Update wallet error", e);
        }
    }

    @Override
    public boolean update(Wallet wallet) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(UPDATE)) {
            statement.setDouble(1, wallet.getBalance());
            statement.setLong(2, wallet.getWalletId());
            return statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Update wallet error", e);
        }
    }
}
