package com.borikov.bullfinch.model.dao.impl;

import com.borikov.bullfinch.model.builder.DiscountBuilder;
import com.borikov.bullfinch.model.dao.ColumnName;
import com.borikov.bullfinch.model.dao.DiscountDao;
import com.borikov.bullfinch.model.entity.Discount;
import com.borikov.bullfinch.model.exception.DaoException;
import com.borikov.bullfinch.model.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code DiscountDaoImpl} class represents discount dao implementation.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class DiscountDaoImpl implements DiscountDao {
    private static final DiscountDaoImpl instance = new DiscountDaoImpl();
    private static final String ADD = "INSERT INTO discount(percent, user_account_id_fk) VALUES (?, ?)";
    private static final String REMOVE = "DELETE FROM discount WHERE discount_id = ?";
    private static final String FIND_BY_USER_LOGIN = "SELECT discount_id, percent FROM discount "
            + "INNER JOIN user_account ON discount.user_account_id_fk = user_account.user_account_id "
            + "WHERE BINARY login LIKE ?";

    private DiscountDaoImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DiscountDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean add(Discount discount) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, discount.getPercent());
            statement.setLong(2, discount.getUser().getUserId());
            boolean isAdded = statement.executeUpdate() > 0;
            ResultSet generatedKeysOrder = statement.getGeneratedKeys();
            if (generatedKeysOrder.next()) {
                discount.setDiscountId(generatedKeysOrder.getLong(1));
            }
            return isAdded;
        } catch (SQLException e) {
            throw new DaoException("Error while adding discount: " + discount, e);
        }
    }

    @Override
    public boolean remove(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while removing discount: id = " + id, e);
        }
    }

    @Override
    public List<Discount> findByUserLogin(String userLogin) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_LOGIN)) {
            statement.setString(1, userLogin);
            List<Discount> discounts = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                discounts.add(createDiscountFromResultSet(resultSet));
            }
            return discounts;
        } catch (SQLException e) {
            throw new DaoException("Error while finding discounts by user login: " + userLogin, e);
        }
    }

    private Discount createDiscountFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ColumnName.DISCOUNT_ID);
        int percent = resultSet.getInt(ColumnName.PERCENT);
        DiscountBuilder discountBuilder = new DiscountBuilder();
        discountBuilder.setDiscountId(id);
        discountBuilder.setPercent(percent);
        return discountBuilder.buildDiscount();
    }
}
