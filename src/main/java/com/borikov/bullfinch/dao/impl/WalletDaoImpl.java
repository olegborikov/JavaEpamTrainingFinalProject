package com.borikov.bullfinch.dao.impl;

import com.borikov.bullfinch.dao.WalletDao;
import com.borikov.bullfinch.dao.pool.ConnectionPool;
import com.borikov.bullfinch.entity.Wallet;
import com.borikov.bullfinch.exception.ConnectionPoolException;
import com.borikov.bullfinch.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WalletDaoImpl implements WalletDao {
    private static final ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private static final String UPDATE = "UPDATE wallet SET balance = ? WHERE wallet_id = ?";

    @Override
    public boolean update(Wallet wallet) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(UPDATE)) {
            statement.setLong(1, wallet.getWalletId());
            statement.setDouble(2, wallet.getBalance());
            return statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Update wallet error", e);
        }
    }
}
