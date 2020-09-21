package com.borikov.bullfinch.dao.impl;

import com.borikov.bullfinch.dao.ColumnName;
import com.borikov.bullfinch.dao.TattooDao;
import com.borikov.bullfinch.dao.pool.ConnectionPool;
import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.exception.ConnectionPoolException;
import com.borikov.bullfinch.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TattooDaoImpl implements TattooDao {
    private static final ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private static final String FIND_ALL_TATTOOS = "SELECT tattoo_id, tattoo_name, " +
            "tattoo_description, tattoo_price,tattoo_rating, is_allowed, is_archived, " +
            "image_name FROM tattoo INNER JOIN image ON tattoo.image_id = image.image_id";

    @Override
    public List<Tattoo> findAll() throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_ALL_TATTOOS)) {
            ResultSet resultSet = statement.executeQuery();
            List<Tattoo> tattoos = new ArrayList<>();
            while (resultSet.next()) {
                Tattoo tattoo = createTattooFromResultSet(resultSet);
                tattoos.add(tattoo);
            }
            return tattoos;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Finding user by login error", e);
        }
    }

    private Tattoo createTattooFromResultSet(ResultSet resultSet) throws SQLException {
        long tattooId = resultSet.getLong(ColumnName.TATTOO_ID);
        String name = resultSet.getString(ColumnName.TATTOO_NAME);
        String description = resultSet.getString(ColumnName.TATTOO_DESCRIPTION);
        double price = resultSet.getDouble(ColumnName.TATTOO_PRICE);
        byte rating = resultSet.getByte(ColumnName.TATTOO_RATING);
        boolean isAllowed = resultSet.getInt(ColumnName.IS_ALLOWED) != 0;
        boolean isArchived = resultSet.getInt(ColumnName.IS_ARCHIVED) != 0;
        String imageName = resultSet.getString(ColumnName.IMAGE_NAME);
        return new Tattoo(tattooId, name, description, price,
                rating, isAllowed, isArchived, imageName);
    }
}
