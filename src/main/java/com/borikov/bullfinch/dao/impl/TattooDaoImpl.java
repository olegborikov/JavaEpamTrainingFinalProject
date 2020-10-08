package com.borikov.bullfinch.dao.impl;

import com.borikov.bullfinch.builder.TattooBuilder;
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
    private static final String FIND_ALL = "SELECT tattoo_id, tattoo_name, " +
            "image_id, image_name FROM tattoo INNER JOIN image " +
            "ON tattoo.image_id_fk = image.image_id";
    private static final String FIND_BY_ALLOWED = "SELECT tattoo_id, tattoo_name, " +
            "image_id, image_name FROM tattoo INNER JOIN image " +
            "ON tattoo.image_id_fk = image.image_id WHERE is_allowed = ?";
    private static final String FIND_BY_ARCHIVED = "SELECT tattoo_id, tattoo_name, " +
            "image_id, image_name FROM tattoo INNER JOIN image " +
            "ON tattoo.image_id_fk = image.image_id WHERE is_archived = ?";
    private static final String FIND_BY_ALLOWED_AND_ARCHIVED = "SELECT tattoo_id, tattoo_name, " +
            "image_id, image_name FROM tattoo INNER JOIN image " +
            "ON tattoo.image_id_fk = image.image_id WHERE is_allowed = ? AND is_archived = ?";
    private static final String FIND_BY_NAME_AND_ALLOWED_AND_ARCHIVED = "SELECT tattoo_id, tattoo_name, " +
            "image_id, image_name FROM tattoo INNER JOIN image ON tattoo.image_id_fk = image.image_id " +
            "WHERE tattoo_name LIKE ? AND is_allowed = ? AND is_archived = ?";
    private static final String FIND_BY_ID_AND_ALLOWED_AND_ARCHIVED = "SELECT tattoo_id, tattoo_name, " +
            "tattoo_description, tattoo_price,tattoo_rating, is_allowed, is_archived, " +
            "image_id, image_name FROM tattoo INNER JOIN image ON tattoo.image_id_fk = image.image_id " +
            "WHERE tattoo_id = ? AND is_allowed = ? AND is_archived = ?";
    private static final String FIND_BY_ID = "SELECT tattoo_id, tattoo_name, " +
            "tattoo_description, tattoo_price,tattoo_rating, is_allowed, is_archived, " +
            "image_id, image_name FROM tattoo INNER JOIN image ON tattoo.image_id_fk = image.image_id " +
            "WHERE tattoo_id = ?";
    private static final String OFFER = "INSERT INTO tattoo (tattoo_name, tattoo_description, " +
            "tattoo_rating, is_allowed, is_archived, image_id_fk) VALUES (?, ?, 5, 0, 0, ?)";
    private static final String ALLOW = "UPDATE tattoo SET is_allowed = 1 WHERE tattoo_id = ?";
    private static final String DELETE = "DELETE FROM tattoo WHERE tattoo_id = ?";
    private static final String ADD_IMAGE = "INSERT INTO image (image_name) VALUES (?)";
    private static final String PERCENT = "%";

    @Override
    public List<Tattoo> findAll() throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            List<Tattoo> tattoos = new ArrayList<>();
            while (resultSet.next()) {
                TattooBuilder tattooBuilder = new TattooBuilder();
                tattooBuilder.setTattooId(resultSet.getLong(ColumnName.TATTOO_ID));
                tattooBuilder.setName(resultSet.getString(ColumnName.TATTOO_NAME));
                tattooBuilder.setImage(new Image(resultSet.getLong(ColumnName.IMAGE_ID),
                        resultSet.getString(ColumnName.IMAGE_NAME)));
                Tattoo tattoo = tattooBuilder.getTattoo();
                tattoos.add(tattoo);
            }
            return tattoos;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Finding all tattoos error", e);
        }
    }

    @Override
    public List<Tattoo> findByAllowed(boolean isAllowed) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_ALLOWED)) {
            statement.setInt(1, isAllowed ? 1 : 0);
            ResultSet resultSet = statement.executeQuery();
            List<Tattoo> tattoos = new ArrayList<>();
            while (resultSet.next()) {
                TattooBuilder tattooBuilder = new TattooBuilder();
                tattooBuilder.setTattooId(resultSet.getLong(ColumnName.TATTOO_ID));
                tattooBuilder.setName(resultSet.getString(ColumnName.TATTOO_NAME));
                tattooBuilder.setImage(new Image(resultSet.getLong(ColumnName.IMAGE_ID),
                        resultSet.getString(ColumnName.IMAGE_NAME)));
                Tattoo tattoo = tattooBuilder.getTattoo();
                tattoos.add(tattoo);
            }
            return tattoos;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Finding all tattoos error", e);
        }
    }

    @Override
    public List<Tattoo> findByArchived(boolean isArchived) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_ARCHIVED)) {
            statement.setInt(1, isArchived ? 1 : 0);
            ResultSet resultSet = statement.executeQuery();
            List<Tattoo> tattoos = new ArrayList<>();
            while (resultSet.next()) {
                TattooBuilder tattooBuilder = new TattooBuilder();// TODO: 07.10.2020 to method
                tattooBuilder.setTattooId(resultSet.getLong(ColumnName.TATTOO_ID));
                tattooBuilder.setName(resultSet.getString(ColumnName.TATTOO_NAME));
                tattooBuilder.setImage(new Image(resultSet.getLong(ColumnName.IMAGE_ID),
                        resultSet.getString(ColumnName.IMAGE_NAME)));
                Tattoo tattoo = tattooBuilder.getTattoo();
                tattoos.add(tattoo);
            }
            return tattoos;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Finding all tattoos error", e);
        }
    }

    @Override
    public List<Tattoo> findByAllowedAndArchived(
            boolean isAllowed, boolean isArchived) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_ALLOWED_AND_ARCHIVED)) {
            statement.setInt(1, isAllowed ? 1 : 0);
            statement.setInt(2, isArchived ? 1 : 0);
            ResultSet resultSet = statement.executeQuery();
            List<Tattoo> tattoos = new ArrayList<>();
            while (resultSet.next()) {
                TattooBuilder tattooBuilder = new TattooBuilder();
                tattooBuilder.setTattooId(resultSet.getLong(ColumnName.TATTOO_ID));
                tattooBuilder.setName(resultSet.getString(ColumnName.TATTOO_NAME));
                tattooBuilder.setImage(new Image(resultSet.getLong(ColumnName.IMAGE_ID),
                        resultSet.getString(ColumnName.IMAGE_NAME)));
                Tattoo tattoo = tattooBuilder.getTattoo();
                tattoos.add(tattoo);
            }
            return tattoos;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Finding tattoos by allowed error", e);
        }
    }

    @Override
    public List<Tattoo> findByNameAndAllowedAndArchived(
            String name, boolean isAllowed, boolean isArchived) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_NAME_AND_ALLOWED_AND_ARCHIVED)) {
            statement.setString(1, PERCENT + name + PERCENT);
            statement.setInt(2, isAllowed ? 1 : 0);
            statement.setInt(3, isArchived ? 1 : 0);
            ResultSet resultSet = statement.executeQuery();
            List<Tattoo> tattoos = new ArrayList<>();
            while (resultSet.next()) {
                TattooBuilder tattooBuilder = new TattooBuilder();
                tattooBuilder.setTattooId(resultSet.getLong(ColumnName.TATTOO_ID));
                tattooBuilder.setName(resultSet.getString(ColumnName.TATTOO_NAME));
                tattooBuilder.setImage(new Image(resultSet.getLong(ColumnName.IMAGE_ID),
                        resultSet.getString(ColumnName.IMAGE_NAME)));
                Tattoo tattoo = tattooBuilder.getTattoo();
                tattoos.add(tattoo);
            }
            return tattoos;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Finding tattoos by name error", e);
        }
    }

    @Override
    public Optional<Tattoo> findByIdAndAllowedAndArchived(
            long id, boolean isAllowed, boolean isArchived) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_ID_AND_ALLOWED_AND_ARCHIVED)) {
            statement.setLong(1, id);
            statement.setInt(2, isAllowed ? 1 : 0);
            statement.setInt(3, isArchived ? 1 : 0);
            ResultSet resultSet = statement.executeQuery();
            Optional<Tattoo> tattooOptional = Optional.empty();
            if (resultSet.next()) {
                TattooBuilder tattooBuilder = new TattooBuilder();
                tattooBuilder.setTattooId(resultSet.getLong(ColumnName.TATTOO_ID));
                tattooBuilder.setName(resultSet.getString(ColumnName.TATTOO_NAME));
                tattooBuilder.setDescription(resultSet.getString(ColumnName.TATTOO_DESCRIPTION));
                tattooBuilder.setPrice(resultSet.getDouble(ColumnName.TATTOO_PRICE));
                tattooBuilder.setRating(resultSet.getByte(ColumnName.TATTOO_RATING));
                tattooBuilder.setImage(new Image(resultSet.getLong(ColumnName.IMAGE_ID),
                        resultSet.getString(ColumnName.IMAGE_NAME)));
                Tattoo tattoo = tattooBuilder.getTattoo();
                tattooOptional = Optional.of(tattoo);
            }
            return tattooOptional;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Finding tattoos by id error", e);
        }
    }

    @Override
    public Optional<Tattoo> findById(long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<Tattoo> tattooOptional = Optional.empty();
            if (resultSet.next()) {
                TattooBuilder tattooBuilder = new TattooBuilder();
                tattooBuilder.setTattooId(resultSet.getLong(ColumnName.TATTOO_ID));
                tattooBuilder.setName(resultSet.getString(ColumnName.TATTOO_NAME));
                tattooBuilder.setDescription(resultSet.getString(ColumnName.TATTOO_DESCRIPTION));
                tattooBuilder.setPrice(resultSet.getDouble(ColumnName.TATTOO_PRICE));
                tattooBuilder.setRating(resultSet.getByte(ColumnName.TATTOO_RATING));
                tattooBuilder.setAllowed(resultSet.getInt(ColumnName.IS_ALLOWED) != 0);
                tattooBuilder.setArchived(resultSet.getInt(ColumnName.IS_ARCHIVED) != 0);
                tattooBuilder.setImage(new Image(resultSet.getLong(ColumnName.IMAGE_ID),
                        resultSet.getString(ColumnName.IMAGE_NAME)));
                Tattoo tattoo = tattooBuilder.getTattoo();
                tattooOptional = Optional.of(tattoo);
            }
            return tattooOptional;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Finding tattoos by id error", e);
        }
    }

    @Override
    public boolean offer(Tattoo tattoo) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statementImage =
                     connection.prepareStatement(ADD_IMAGE, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statementTattoo =
                     connection.prepareStatement(OFFER, Statement.RETURN_GENERATED_KEYS)) {
            statementImage.setString(1, tattoo.getImage().getName());
            statementImage.executeUpdate();
            ResultSet generatedKeysImage = statementImage.getGeneratedKeys();
            if (generatedKeysImage.next()) {
                tattoo.getImage().setImageId(generatedKeysImage.getLong(1));
            }
            statementTattoo.setString(1, tattoo.getName());
            statementTattoo.setString(2, tattoo.getDescription());
            statementTattoo.setLong(3, tattoo.getImage().getImageId());
            boolean result = statementTattoo.executeUpdate() > 0;
            ResultSet generatedKeysTattoo = statementTattoo.getGeneratedKeys();
            if (generatedKeysTattoo.next()) {
                tattoo.setTattooId(generatedKeysTattoo.getLong(1));
            }
            return result;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Offer tattoo error", e);
        }
    }

    @Override
    public boolean allow(long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(ALLOW)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Offer tattoo error", e);
        }
    }

    @Override
    public boolean delete(long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Offer tattoo error", e);
        }
    }
}
