package com.borikov.bullfinch.dao.impl;

import com.borikov.bullfinch.dao.ColumnName;
import com.borikov.bullfinch.dao.TattooDao;
import com.borikov.bullfinch.dao.pool.ConnectionPool;
import com.borikov.bullfinch.entity.Image;
import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.exception.ConnectionPoolException;
import com.borikov.bullfinch.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TattooDaoImpl implements TattooDao {
    private static final ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private static final String FIND_ALL_TATTOOS = "SELECT tattoo_id, tattoo_name, " +
            "tattoo_description, tattoo_price, tattoo_rating, is_allowed, is_archived, " +
            "image_id, image_name FROM tattoo INNER JOIN image ON tattoo.image_id_fk = image.image_id"; // TODO: 07.10.2020 left only id, name, image
    private static final String FIND_TATTOOS_BY_ALLOWED = "SELECT tattoo_id, tattoo_name, " +
            "tattoo_description, tattoo_price, tattoo_rating, is_allowed, is_archived, " +
            "image_id, image_name FROM tattoo INNER JOIN image ON tattoo.image_id_fk = image.image_id " +
            "WHERE is_allowed=1"; // TODO: 07.10.2020 left only id, name, image
    private static final String FIND_TATTOOS_BY_NAME = "SELECT tattoo_id, tattoo_name, " +
            "tattoo_description, tattoo_price,tattoo_rating, is_allowed, is_archived, " +
            "image_id, image_name FROM tattoo INNER JOIN image ON tattoo.image_id_fk = image.image_id " +
            "WHERE tattoo_name LIKE ?";
    private static final String FIND_TATTOOS_BY_ID = "SELECT tattoo_id, tattoo_name, " +
            "tattoo_description, tattoo_price,tattoo_rating, is_allowed, is_archived, " +
            "image_id, image_name FROM tattoo INNER JOIN image ON tattoo.image_id_fk = image.image_id " +
            "WHERE tattoo_id = ?";
    private static final String ADD_TATTOO = "INSERT INTO tattoo (tattoo_name, tattoo_description, " +
            "tattoo_rating, is_allowed, is_archived, image_id_fk) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String ADD_IMAGE = "INSERT INTO image (image_name) VALUES (?)";
    private static final String PERCENT = "%";

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
            throw new DaoException("Finding tattoos error", e);
        }
    }

    @Override
    public List<Tattoo> findByAllowed() throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_TATTOOS_BY_ALLOWED)) {
            ResultSet resultSet = statement.executeQuery();
            List<Tattoo> tattoos = new ArrayList<>();
            while (resultSet.next()) {
                Tattoo tattoo = createTattooFromResultSet(resultSet);
                tattoos.add(tattoo);
            }
            return tattoos;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Finding tattoos by allowed error", e);
        }
    }

    @Override
    public List<Tattoo> findByName(String name) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_TATTOOS_BY_NAME)) {
            statement.setString(1, PERCENT + name + PERCENT);
            ResultSet resultSet = statement.executeQuery();
            List<Tattoo> tattoos = new ArrayList<>();
            while (resultSet.next()) {
                Tattoo tattoo = createTattooFromResultSet(resultSet);
                tattoos.add(tattoo);
            }
            return tattoos;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Finding tattoos by name error", e);
        }
    }

    @Override
    public Optional<Tattoo> findById(long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_TATTOOS_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<Tattoo> tattooOptional = Optional.empty();
            if (resultSet.next()) {
                Tattoo tattoo = createTattooFromResultSet(resultSet);
                tattooOptional = Optional.of(tattoo);
            }
            return tattooOptional;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Finding tattoos by id error", e);
        }
    }

    @Override
    public boolean add(Tattoo tattoo) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statementImage =
                     connection.prepareStatement(ADD_IMAGE, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statementTattoo =
                     connection.prepareStatement(ADD_TATTOO, Statement.RETURN_GENERATED_KEYS)) {
            statementImage.setString(1, tattoo.getImage().getName());
            statementImage.executeUpdate();
            ResultSet generatedKeysImage = statementImage.getGeneratedKeys();
            if (generatedKeysImage.next()) {
                tattoo.getImage().setImageId(generatedKeysImage.getLong(1));
            }
            statementTattoo.setString(1, tattoo.getName());
            statementTattoo.setString(2, tattoo.getDescription());
            statementTattoo.setInt(3, 5);
            statementTattoo.setInt(4, 0);
            statementTattoo.setInt(5, 0);
            statementTattoo.setLong(6, tattoo.getImage().getImageId());
            boolean result = statementTattoo.executeUpdate() > 0;
            ResultSet generatedKeysTattoo = statementTattoo.getGeneratedKeys();
            if (generatedKeysTattoo.next()) {
                tattoo.setTattooId(generatedKeysTattoo.getLong(1));
            }
            return result;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Add tattoo error", e);
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
        Image image = createImageFromResultSet(resultSet);
        return new Tattoo(tattooId, name, description, price, // TODO: 07.10.2020 to builder
                rating, isAllowed, isArchived, image);
    }

    private Image createImageFromResultSet(ResultSet resultSet) throws SQLException {
        long imageId = resultSet.getLong(ColumnName.IMAGE_ID);
        String name = resultSet.getString(ColumnName.IMAGE_NAME);
        return new Image(imageId, name);
    }
}
