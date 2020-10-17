package com.borikov.bullfinch.dao.impl;

import com.borikov.bullfinch.builder.TattooBuilder;
import com.borikov.bullfinch.builder.UserBuilder;
import com.borikov.bullfinch.dao.ColumnName;
import com.borikov.bullfinch.dao.TattooDao;
import com.borikov.bullfinch.dao.pool.ConnectionPool;
import com.borikov.bullfinch.entity.Image;
import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.exception.ConnectionPoolException;
import com.borikov.bullfinch.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TattooDaoImpl implements TattooDao {
    private static final ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private static final String FIND_ALL = "SELECT tattoo_id, tattoo_name, " +
            "image_id, image_name FROM tattoo INNER JOIN image " +
            "ON tattoo.image_id_fk = image.image_id";
    private static final String FIND_BY_NAME = "SELECT tattoo_id, tattoo_name, " +
            "image_id, image_name FROM tattoo INNER JOIN image " +
            "ON tattoo.image_id_fk = image.image_id WHERE tattoo_name LIKE ?";
    private static final String FIND_BY_ALLOWED = "SELECT tattoo_id, tattoo_name, " +
            "image_id, image_name FROM tattoo INNER JOIN image " +
            "ON tattoo.image_id_fk = image.image_id WHERE is_allowed = ?";
    private static final String FIND_BY_ARCHIVED = "SELECT tattoo_id, tattoo_name, " +
            "image_id, image_name FROM tattoo INNER JOIN image " +
            "ON tattoo.image_id_fk = image.image_id WHERE is_archived = ?";
    private static final String FIND_ALL_CATALOG = "SELECT tattoo_id, tattoo_name, " +
            "image_id, image_name FROM tattoo INNER JOIN image " +
            "ON tattoo.image_id_fk = image.image_id " +
            "WHERE is_allowed = 1 AND is_archived = 0";
    private static final String FIND_BY_NAME_CATALOG = "SELECT tattoo_id, tattoo_name, " +
            "image_id, image_name FROM tattoo INNER JOIN image " +
            "ON tattoo.image_id_fk = image.image_id " +
            "WHERE tattoo_name LIKE ? AND is_allowed = 1 AND is_archived = 0";
    private static final String FIND_BY_ID_CATALOG = "SELECT tattoo_id, tattoo_name, " +
            "tattoo_description, tattoo_price,tattoo_rating, is_allowed, " +
            "is_archived, image_id, image_name FROM tattoo INNER JOIN image ON " +
            "tattoo.image_id_fk = image.image_id " +
            "WHERE tattoo_id = ? AND is_allowed = 1 AND is_archived = 0";
    private static final String FIND_BY_ID = "SELECT tattoo_id, tattoo_name, " +
            "tattoo_description, tattoo_price,tattoo_rating, is_allowed, " +
            "is_archived, image_id, image_name, login FROM tattoo " +
            "INNER JOIN image ON tattoo.image_id_fk = image.image_id " +
            "INNER JOIN user_account ON tattoo.user_account_id_fk = " +
            "user_account.user_account_id WHERE tattoo_id = ?";
    private static final String OFFER = "INSERT INTO tattoo (tattoo_name, tattoo_description, " +
            "tattoo_price, tattoo_rating, is_allowed, is_archived, " +
            "image_id_fk, user_account_id_fk) VALUES (?, ?, ?, 5, 0, 0, ?, " +
            "(SELECT user_account_id FROM user_account WHERE BINARY login LIKE ?))";
    private static final String ADD = "INSERT INTO tattoo (tattoo_name, tattoo_description, " +
            "tattoo_price, tattoo_rating, is_allowed, is_archived, " +
            "image_id_fk, user_account_id_fk) VALUES (?, ?, ?, 5, 1, 0, ?," +
            "(SELECT user_account_id FROM user_account WHERE BINARY login LIKE ?))";
    private static final String ALLOW = "UPDATE tattoo SET is_allowed = 1 " +
            "WHERE tattoo_id = ?";
    private static final String DELETE = "DELETE FROM tattoo WHERE tattoo_id = ?";
    private static final String ARCHIVE = "UPDATE tattoo SET is_archived = 1 " +
            "WHERE tattoo_id = ?";
    private static final String UNARCHIVE = "UPDATE tattoo SET is_archived = 0 " +
            "WHERE tattoo_id = ?";
    private static final String UPDATE = "UPDATE tattoo SET tattoo_name = ?, " +
            "tattoo_description = ?, tattoo_price = ? WHERE tattoo_id = ?";
    private static final String PERCENT = "%";

    @Override
    public boolean add(Tattoo tattoo, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(ADD)) {
            statement.setString(1, tattoo.getName());
            statement.setString(2, tattoo.getDescription());
            statement.setDouble(3, tattoo.getPrice());
            statement.setLong(4, tattoo.getImage().getImageId());
            statement.setString(5, tattoo.getUser().getLogin());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Adding tattoo error", e);
        }
    }

    @Override
    public boolean remove(long id, Connection connection) throws DaoException {
        try (PreparedStatement statement =
                     connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Delete tattoo error", e);
        }
    }

    @Override
    public boolean update(Tattoo tattoo) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(UPDATE)) {
            statement.setString(1, tattoo.getName());
            statement.setString(2, tattoo.getDescription());
            statement.setDouble(3, tattoo.getPrice());
            statement.setLong(4, tattoo.getTattooId());
            return statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Update tattoo error", e);
        }
    }

    @Override
    public boolean offer(Tattoo tattoo, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(OFFER)) {
            statement.setString(1, tattoo.getName());
            statement.setString(2, tattoo.getDescription());
            statement.setDouble(3, tattoo.getPrice());
            statement.setLong(4, tattoo.getImage().getImageId());
            statement.setString(5, tattoo.getUser().getLogin());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Offering tattoo error", e);
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
            throw new DaoException("Allow tattoo error", e);
        }
    }

    @Override
    public boolean archive(long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(ARCHIVE)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Archive tattoo error", e);
        }
    }

    @Override
    public boolean unarchive(long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(UNARCHIVE)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Unarchive tattoo error", e);
        }
    }

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
                UserBuilder userBuilder = new UserBuilder();
                userBuilder.setLogin(resultSet.getString(ColumnName.LOGIN));
                tattooBuilder.setUser(userBuilder.getUser());
                Tattoo tattoo = tattooBuilder.getTattoo();
                tattooOptional = Optional.of(tattoo);
            }
            return tattooOptional;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Finding tattoos by id error", e);
        }
    }

    @Override
    public List<Tattoo> findByName(String name) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_NAME)) {
            statement.setString(1, PERCENT + name + PERCENT);
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
            throw new DaoException("Finding tattoos error", e);
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
            throw new DaoException("Finding tattoos error", e);
        }
    }

    @Override
    public List<Tattoo> findAllCatalog() throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_ALL_CATALOG)) {
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
            throw new DaoException("Finding tattoos error", e);
        }
    }

    @Override
    public Optional<Tattoo> findByIdCatalog(long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_ID_CATALOG)) {
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
    public List<Tattoo> findByNameCatalog(String name) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_NAME_CATALOG)) {
            statement.setString(1, PERCENT + name + PERCENT);
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
}
