package com.borikov.bullfinch.model.dao.impl;

import com.borikov.bullfinch.model.builder.OrderBuilder;
import com.borikov.bullfinch.model.builder.TattooBuilder;
import com.borikov.bullfinch.model.builder.UserBuilder;
import com.borikov.bullfinch.model.dao.ColumnName;
import com.borikov.bullfinch.model.dao.OrderDao;
import com.borikov.bullfinch.model.entity.Image;
import com.borikov.bullfinch.model.entity.Order;
import com.borikov.bullfinch.model.exception.DaoException;
import com.borikov.bullfinch.model.pool.ConnectionPool;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.a.result.NativeResultset;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The {@code OrderDaoImpl} class represents order dao implementation.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
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
    private static final String FIND_BY_DATES = "SELECT tattoo_order_id, tattoo_name, date, is_confirmed, "
            + "tattoo_order_price FROM tattoo_order INNER JOIN tattoo "
            + "ON tattoo_order.tattoo_id_fk = tattoo.tattoo_id " +
            "WHERE date BETWEEN ? AND ? ORDER BY is_confirmed";
    private static final String FIND_BY_USER_LOGIN = "SELECT tattoo_order_id, tattoo_name, date, is_confirmed,  "
            + "tattoo_order_price FROM tattoo_order INNER JOIN tattoo "
            + "ON tattoo_order.tattoo_id_fk = tattoo.tattoo_id INNER JOIN user_account "
            + "ON tattoo_order.user_account_id_fk = user_account.user_account_id WHERE BINARY login LIKE ?";

    private OrderDaoImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
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
                orders.add(createOrderFromResultSet(resultSet));
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
                order = Optional.of(createOrderFromResultSet(resultSet));
            }
            return order;
        } catch (SQLException e) {
            throw new DaoException("Error while finding order by id: " + id, e);
        }
    }

    @Override
    public List<Order> findByDates(LocalDate beginDate, LocalDate endDate) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_DATES)) {
            Date beginDateParse = Date.valueOf(beginDate);
            Date endDateParse = Date.valueOf(endDate);
            statement.setLong(1, beginDateParse.getTime());
            statement.setLong(2, endDateParse.getTime());
            ResultSet resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(createOrderFromResultSet(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            throw new DaoException("Error while finding orders by dates: begin date = "
                    + beginDate + ", end date = " + endDate, e);
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
                orders.add(createOrderFromResultSet(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            throw new DaoException("Error while finding orders by user login: " + userLogin, e);
        }
    }

    private Order createOrderFromResultSet(ResultSet resultSet) throws SQLException {
        ColumnDefinition columnDefinition = ((NativeResultset) resultSet).getColumnDefinition();
        OrderBuilder orderBuilder = new OrderBuilder();
        long id = resultSet.getLong(ColumnName.TATTOO_ORDER_ID);
        orderBuilder.setOrderId(id);
        Date date = new Date(resultSet.getLong(ColumnName.DATE));
        orderBuilder.setDate(date.toLocalDate());
        boolean isConfirmed = resultSet.getInt(ColumnName.IS_CONFIRMED) != 0;
        orderBuilder.setConfirmed(isConfirmed);
        double price = resultSet.getDouble(ColumnName.TATTOO_ORDER_PRICE);
        orderBuilder.setPrice(price);
        String tattooName = resultSet.getString(ColumnName.TATTOO_NAME);
        TattooBuilder tattooBuilder = new TattooBuilder();
        tattooBuilder.setName(tattooName);
        if (columnDefinition.findColumn(ColumnName.IMAGE_NAME, true, 1) != -1) {
            String imageName = resultSet.getString(ColumnName.IMAGE_NAME);
            tattooBuilder.setImage(new Image(null, imageName));
        }
        orderBuilder.setTattoo(tattooBuilder.getTattoo());
        if (columnDefinition.findColumn(ColumnName.TATTOO_ORDER_DESCRIPTION, true, 1) != -1) {
            String description = resultSet.getString(ColumnName.TATTOO_ORDER_DESCRIPTION);
            orderBuilder.setDescription(description);
        }
        if (columnDefinition.findColumn(ColumnName.LOGIN, true, 1) != -1) {
            String login = resultSet.getString(ColumnName.LOGIN);
            UserBuilder userBuilder = new UserBuilder();
            userBuilder.setLogin(login);
            orderBuilder.setUser(userBuilder.getUser());
        }
        return orderBuilder.getOrder();
    }
}
