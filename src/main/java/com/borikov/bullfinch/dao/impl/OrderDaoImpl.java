package com.borikov.bullfinch.dao.impl;

import com.borikov.bullfinch.builder.OrderBuilder;
import com.borikov.bullfinch.builder.TattooBuilder;
import com.borikov.bullfinch.dao.ColumnName;
import com.borikov.bullfinch.dao.OrderDao;
import com.borikov.bullfinch.dao.pool.ConnectionPool;
import com.borikov.bullfinch.entity.Order;
import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.exception.ConnectionPoolException;
import com.borikov.bullfinch.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static final ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private static final String ADD = "INSERT INTO tattoo_order (tattoo_order_price, date, " +
            "tattoo_order_description, tattoo_id_fk, user_account_id_fk) VALUES (?, ?, ?, ?, " +
            "(SELECT user_account_id FROM user_account WHERE BINARY login LIKE ?))";
    private static final String FIND_BY_LOGIN = "SELECT tattoo_name, date, tattoo_order_price " +
            "FROM tattoo_order INNER JOIN tattoo ON tattoo_order.tattoo_id_fk = tattoo.tattoo_id " +
            "INNER JOIN user_account ON tattoo_order.user_account_id_fk = " +
            "user_account.user_account_id WHERE login = ?";

    @Override
    public boolean add(Order order) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDouble(1, order.getPrice());
            statement.setLong(2, Date.valueOf(order.getDate()).getTime());
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

    @Override
    public List<Order> findByUserLogin(String userLogin) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN)) {
            statement.setString(1, userLogin);
            ResultSet resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                OrderBuilder orderBuilder = new OrderBuilder();
                TattooBuilder tattooBuilder = new TattooBuilder();
                tattooBuilder.setName(resultSet.getString(ColumnName.TATTOO_NAME));
                Tattoo tattoo = tattooBuilder.getTattoo();
                orderBuilder.setTattoo(tattoo);
                Date date = new Date(resultSet.getLong(ColumnName.DATE));
                orderBuilder.setDate(date.toLocalDate());
                orderBuilder.setPrice(resultSet.getDouble(ColumnName.TATTOO_ORDER_PRICE));
                Order order = orderBuilder.getOrder();
                orders.add(order);
            }
            return orders;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Finding orders by user login error", e);
        }
    }
}
