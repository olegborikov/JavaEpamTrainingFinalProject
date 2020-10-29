package com.borikov.bullfinch.model.dao.impl;

import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.model.builder.OrderBuilder;
import com.borikov.bullfinch.model.builder.TattooBuilder;
import com.borikov.bullfinch.model.builder.UserBuilder;
import com.borikov.bullfinch.model.dao.ColumnName;
import com.borikov.bullfinch.model.dao.OrderDao;
import com.borikov.bullfinch.model.dao.pool.ConnectionPool;
import com.borikov.bullfinch.model.entity.Image;
import com.borikov.bullfinch.model.entity.Order;
import com.borikov.bullfinch.model.entity.Tattoo;
import com.borikov.bullfinch.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    private static final OrderDaoImpl INSTANCE = new OrderDaoImpl();
    private static final String ADD = "INSERT INTO tattoo_order (tattoo_order_price, date, "
            + "tattoo_order_description, is_confirmed, tattoo_id_fk, user_account_id_fk) VALUES (?, ?, ?, 0, ?, "
            + "(SELECT user_account_id FROM user_account WHERE BINARY login LIKE ?))";
    private static final String REMOVE = "DELETE FROM tattoo_order WHERE tattoo_order_id = ?";
    private static final String SUBMIT = "UPDATE tattoo_order SET is_confirmed = 1 WHERE tattoo_order_id = ?";
    private static final String FIND_ALL = "SELECT tattoo_order_id, tattoo_name, date, is_confirmed, "
            + "tattoo_order_price FROM tattoo_order INNER JOIN tattoo "
            + "ON tattoo_order.tattoo_id_fk = tattoo.tattoo_id ORDER BY is_confirmed";
    private static final String FIND_BY_ID = "SELECT tattoo_order_id, tattoo_order_price, date, "
            + "tattoo_order_description, is_confirmed, image_name, tattoo_name, login FROM tattoo_order "
            + "INNER JOIN tattoo ON tattoo_order.tattoo_id_fk = tattoo.tattoo_id INNER JOIN user_account "
            + "ON tattoo_order.user_account_id_fk = user_account.user_account_id "
            + "INNER JOIN image ON tattoo.image_id_fk = image.image_id WHERE tattoo_order_id = ?";
    private static final String FIND_BY_USER_LOGIN = "SELECT tattoo_order_id, tattoo_name, date, is_confirmed,  "
            + "tattoo_order_price FROM tattoo_order INNER JOIN tattoo "
            + "ON tattoo_order.tattoo_id_fk = tattoo.tattoo_id INNER JOIN user_account "
            + "ON tattoo_order.user_account_id_fk = user_account.user_account_id WHERE login = ?";

    private OrderDaoImpl() {
    }

    public static OrderDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(Order order) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDouble(1, order.getPrice());
            Date date = Date.valueOf(order.getDate());
            statement.setLong(2, date.getTime());
            statement.setString(3, order.getDescription());
            statement.setLong(4, order.getTattoo().getTattooId());
            statement.setString(5, order.getUser().getLogin());
            boolean result = statement.executeUpdate() > 0;
            ResultSet generatedKeysOrder = statement.getGeneratedKeys();
            if (generatedKeysOrder.next()) {
                order.setOrderId(generatedKeysOrder.getLong(1));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException("Error while adding order: " + order, e);
        }
    }

    @Override
    public boolean remove(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while removing order: id = " + id, e);
        }
    }

    @Override
    public boolean submit(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SUBMIT)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while submitting order: id = " + id, e);
        }
    }

    @Override
    public List<Order> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(createPartOrderFromResultSet(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            throw new DaoException("Error while finding all orders", e);
        }
    }

    @Override
    public Optional<Order> findById(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<Order> order = Optional.empty();
            if (resultSet.next()) {
                order = Optional.of(createFullOrderFromResultSet(resultSet));
            }
            return order;
        } catch (SQLException e) {
            throw new DaoException("Error while finding order by id: " + id, e);
        }
    }

    @Override
    public List<Order> findByUserLogin(String userLogin) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_LOGIN)) {
            statement.setString(1, userLogin);
            ResultSet resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(createPartOrderFromResultSet(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            throw new DaoException("Error while finding orders by user login: " + userLogin, e);
        }
    }

    private Order createPartOrderFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ColumnName.TATTOO_ORDER_ID);
        String tattooName = resultSet.getString(ColumnName.TATTOO_NAME);
        Date date = new Date(resultSet.getLong(ColumnName.DATE));
        boolean isConfirmed = resultSet.getInt(ColumnName.IS_CONFIRMED) != 0;
        double price = resultSet.getDouble(ColumnName.TATTOO_ORDER_PRICE);
        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setOrderId(id);
        TattooBuilder tattooBuilder = new TattooBuilder();
        tattooBuilder.setName(tattooName);
        Tattoo tattoo = tattooBuilder.getTattoo();
        orderBuilder.setTattoo(tattoo);
        orderBuilder.setDate(date.toLocalDate());
        orderBuilder.setConfirmed(isConfirmed);
        orderBuilder.setPrice(price);
        return orderBuilder.getOrder();
    }

    private Order createFullOrderFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ColumnName.TATTOO_ORDER_ID);
        double price = resultSet.getDouble(ColumnName.TATTOO_ORDER_PRICE);
        Date date = new Date(resultSet.getLong(ColumnName.DATE));
        String description = resultSet.getString(ColumnName.TATTOO_ORDER_DESCRIPTION);
        boolean isConfirmed = resultSet.getInt(ColumnName.IS_CONFIRMED) != 0;
        String tattooName = resultSet.getString(ColumnName.TATTOO_NAME);
        String imageName = resultSet.getString(ColumnName.IMAGE_NAME);
        String login = resultSet.getString(ColumnName.LOGIN);
        Image image = new Image(null, imageName);
        TattooBuilder tattooBuilder = new TattooBuilder();
        tattooBuilder.setName(tattooName);
        tattooBuilder.setImage(image);
        Tattoo tattoo = tattooBuilder.getTattoo();
        UserBuilder userBuilder = new UserBuilder();
        userBuilder.setLogin(login);
        User user = userBuilder.getUser();
        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setOrderId(id);
        orderBuilder.setPrice(price);
        orderBuilder.setDate(date.toLocalDate());
        orderBuilder.setDescription(description);
        orderBuilder.setConfirmed(isConfirmed);
        orderBuilder.setTattoo(tattoo);
        orderBuilder.setUser(user);
        return orderBuilder.getOrder();
    }
}
