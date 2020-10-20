package com.borikov.bullfinch.dao.impl;

import com.borikov.bullfinch.dao.ImageDao;
import com.borikov.bullfinch.entity.Image;
import com.borikov.bullfinch.exception.DaoException;

import java.sql.*;

public class ImageDaoImpl implements ImageDao {
    private static final String ADD = "INSERT INTO image (image_name) VALUES (?)";
    private static final String REMOVE = "DELETE FROM image WHERE image_id = ?";

    @Override
    public boolean add(Image image, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(
                ADD, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, image.getName());
            boolean result = statement.executeUpdate() > 0;
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                image.setImageId(generatedKeys.getLong(1));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException("Error while adding image: " + image, e);
        }
    }

    @Override
    public boolean remove(long id, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(REMOVE)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while removing image with id: " + id, e);
        }
    }
}
