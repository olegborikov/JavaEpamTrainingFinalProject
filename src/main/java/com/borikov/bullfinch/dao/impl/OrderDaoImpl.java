package com.borikov.bullfinch.dao.impl;

import com.borikov.bullfinch.dao.OrderDao;
import com.borikov.bullfinch.dao.pool.ConnectionPool;
import com.borikov.bullfinch.entity.Order;
import com.borikov.bullfinch.exception.ConnectionPoolException;
import com.borikov.bullfinch.exception.DaoException;

import java.sql.*;

public class OrderDaoImpl implements OrderDao {
    private static final ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private static final String ADD = "INSERT INTO tattoo_order (order_price, date, " +
            "order_description, tattoo_id_fk, user_account_id_fk) VALUES (?, ?, ?, ?, " +
            "(SELECT user_account_id FROM user_account WHERE BINARY login LIKE ?))";

    @Override
    public boolean add(Order order) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDouble(1, order.getPrice());
            statement.setLong(2, order.getDate().getTime());
            statement.setString(3, order.getDescription());
            statement.setLong(4, order.getTattoo().getTattooId());
            statement.setString(5, order.getUser().getLogin());
            boolean result = statement.executeUpdate() > 0;
            ResultSet generatedKeysOrder = statement.getGeneratedKeys();
            if (generatedKeysOrder.next()) {
                order.setOrderId(generatedKeysOrder.getLong(1));
            }
            return result;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Adding order error", e);
        }
    }
}
