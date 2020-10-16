package com.borikov.bullfinch.dao.impl;

import com.borikov.bullfinch.builder.OrderBuilder;
import com.borikov.bullfinch.builder.TattooBuilder;
import com.borikov.bullfinch.builder.UserBuilder;
import com.borikov.bullfinch.dao.ColumnName;
import com.borikov.bullfinch.dao.OrderDao;
import com.borikov.bullfinch.dao.pool.ConnectionPool;
import com.borikov.bullfinch.entity.Image;
import com.borikov.bullfinch.entity.Order;
import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.exception.ConnectionPoolException;
import com.borikov.bullfinch.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    private static final ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private static final String ADD = "INSERT INTO tattoo_order (tattoo_order_price, date, " +
            "tattoo_order_description, is_confirmed, tattoo_id_fk, user_account_id_fk) " +
            "VALUES (?, ?, ?, 0, ?, (SELECT user_account_id FROM user_account WHERE BINARY login LIKE ?))";
    private static final String FIND_BY_LOGIN = "SELECT tattoo_order_id, tattoo_name, date, tattoo_order_price " +
            "FROM tattoo_order INNER JOIN tattoo ON tattoo_order.tattoo_id_fk = tattoo.tattoo_id " +
            "INNER JOIN user_account ON tattoo_order.user_account_id_fk = " +
            "user_account.user_account_id WHERE login = ?";
    private static final String FIND_BY_ID = "SELECT tattoo_order_id, tattoo_order_price, date, " +
            "tattoo_order_description, is_confirmed, image_name, tattoo_name, login FROM tattoo_order " +
            "INNER JOIN tattoo ON tattoo_order.tattoo_id_fk = tattoo.tattoo_id " +
            "INNER JOIN user_account ON tattoo_order.user_account_id_fk = " +
            "user_account.user_account_id INNER JOIN image on tattoo.image_id_fk = " +
            "image.image_id WHERE tattoo_order_id = ?";

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
                orderBuilder.setOrderId(resultSet.getLong(ColumnName.TATTOO_ORDER_ID));
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

    @Override
    public Optional<Order> findById(long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<Order> order = Optional.empty();
            if (resultSet.next()) {
                OrderBuilder orderBuilder = new OrderBuilder();
                orderBuilder.setOrderId(resultSet.getLong(ColumnName.TATTOO_ORDER_ID));
                orderBuilder.setPrice(resultSet.getDouble(ColumnName.TATTOO_ORDER_PRICE));
                Date date = new Date(resultSet.getLong(ColumnName.DATE));
                orderBuilder.setDate(date.toLocalDate());
                orderBuilder.setDescription(resultSet.getString(ColumnName.TATTOO_ORDER_DESCRIPTION));
                orderBuilder.setConfirmed(resultSet.getInt(ColumnName.IS_CONFIRMED) != 0);
                TattooBuilder tattooBuilder = new TattooBuilder();
                tattooBuilder.setName(resultSet.getString(ColumnName.TATTOO_NAME));
                tattooBuilder.setImage(new Image(null, resultSet.getString(ColumnName.IMAGE_NAME)));
                Tattoo tattoo = tattooBuilder.getTattoo();
                orderBuilder.setTattoo(tattoo);
                UserBuilder userBuilder = new UserBuilder();
                userBuilder.setLogin(resultSet.getString(ColumnName.LOGIN));
                orderBuilder.setUser(userBuilder.getUser());
                order = Optional.of(orderBuilder.getOrder());
            }
            return order;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Finding orders by user login error", e);
        }
    }
}
