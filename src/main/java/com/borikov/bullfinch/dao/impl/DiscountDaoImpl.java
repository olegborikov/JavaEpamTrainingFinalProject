package com.borikov.bullfinch.dao.impl;

import com.borikov.bullfinch.builder.DiscountBuilder;
import com.borikov.bullfinch.dao.ColumnName;
import com.borikov.bullfinch.dao.DiscountDao;
import com.borikov.bullfinch.dao.pool.ConnectionPool;
import com.borikov.bullfinch.entity.Discount;
import com.borikov.bullfinch.exception.ConnectionPoolException;
import com.borikov.bullfinch.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscountDaoImpl implements DiscountDao {
    private static final ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private static final String ADD = "INSERT INTO discount(discount_percent, " +
            "user_account_id_fk) VALUES (?, ?)";
    private static final String REMOVE = "DELETE FROM discount " +
            "WHERE discount_id = ?";
    private static final String FIND_BY_USER_LOGIN = "SELECT discount_id, discount_percent " +
            "FROM discount INNER JOIN user_account ON discount.user_account_id_fk = " +
            "user_account.user_account_id WHERE login LIKE ?";
    private static final String PERCENT = "%";

    @Override
    public boolean add(Discount discount) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, discount.getDiscountPercent());
            statement.setLong(2, discount.getUser().getUserId());
            boolean result = statement.executeUpdate() > 0;
            ResultSet generatedKeysOrder = statement.getGeneratedKeys();
            if (generatedKeysOrder.next()) {
                discount.setDiscountId(generatedKeysOrder.getLong(1));
            }
            return result;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Adding discount error", e);
        }
    }

    @Override
    public boolean remove(long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(REMOVE)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Removing discount error", e);
        }
    }

    @Override
    public List<Discount> findByUserLogin(String userLogin) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_LOGIN)) {
            statement.setString(1, PERCENT + userLogin + PERCENT);
            List<Discount> discounts = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DiscountBuilder discountBuilder = new DiscountBuilder();
                discountBuilder.setDiscountId(resultSet.getLong(ColumnName.DISCOUNT_ID));
                discountBuilder.setDiscountPercent(resultSet.getInt(ColumnName.DISCOUNT_PERCENT));
                Discount discount = discountBuilder.getDiscount();
                discounts.add(discount);
            }
            return discounts;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Finding discounts by user login error", e);
        }
    }
}
