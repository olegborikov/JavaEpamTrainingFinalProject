package com.borikov.bullfinch.dao.impl;

import com.borikov.bullfinch.builder.TattooBuilder;
import com.borikov.bullfinch.builder.UserBuilder;
import com.borikov.bullfinch.dao.ColumnName;
import com.borikov.bullfinch.dao.TattooDao;
import com.borikov.bullfinch.dao.pool.ConnectionPool;
import com.borikov.bullfinch.entity.Image;
import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.entity.User;
import com.borikov.bullfinch.exception.ConnectionPoolException;
import com.borikov.bullfinch.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TattooDaoImpl implements TattooDao {
    private static final String ADD = "INSERT INTO tattoo (tattoo_name, " +
            "tattoo_description, tattoo_price, is_allowed, is_archived, " +
            "image_id_fk, user_account_id_fk) VALUES (?, ?, ?, 1, 0, ?, " +
            "(SELECT user_account_id FROM user_account WHERE BINARY login LIKE ?))";
    private static final String REMOVE = "DELETE FROM tattoo WHERE tattoo_id = ?";
    private static final String UPDATE = "UPDATE tattoo SET tattoo_name = ?, " +
            "tattoo_description = ?, tattoo_price = ? WHERE tattoo_id = ?";
    private static final String OFFER = "INSERT INTO tattoo (tattoo_name, " +
            "tattoo_description, tattoo_price, is_allowed, is_archived, " +
            "image_id_fk, user_account_id_fk) VALUES (?, ?, ?, 0, 0, ?, " +
            "(SELECT user_account_id FROM user_account WHERE BINARY login LIKE ?))";
    private static final String ALLOW = "UPDATE tattoo SET is_allowed = 1 " +
            "WHERE tattoo_id = ?";
    private static final String ARCHIVE = "UPDATE tattoo SET is_archived = 1 " +
            "WHERE tattoo_id = ?";
    private static final String UNARCHIVE = "UPDATE tattoo SET is_archived = 0 " +
            "WHERE tattoo_id = ?";
    private static final String FIND_ALL = "SELECT tattoo_id, tattoo_name, " +
            "image_id, image_name FROM tattoo INNER JOIN image " +
            "ON tattoo.image_id_fk = image.image_id";
    private static final String FIND_BY_ID = "SELECT tattoo_id, tattoo_name, " +
            "tattoo_description, tattoo_price, is_allowed, is_archived, " +
            "image_id, image_name, login FROM tattoo INNER JOIN image " +
            "ON tattoo.image_id_fk = image.image_id " +
            "INNER JOIN user_account " +
            "ON tattoo.user_account_id_fk = user_account.user_account_id " +
            "WHERE tattoo_id = ?";
    private static final String FIND_BY_NAME = "SELECT tattoo_id, tattoo_name, " +
            "image_id, image_name FROM tattoo INNER JOIN image " +
            "ON tattoo.image_id_fk = image.image_id WHERE tattoo_name LIKE ?";
    private static final String FIND_BY_ALLOWED = "SELECT tattoo_id, tattoo_name, " +
            "image_id, image_name FROM tattoo INNER JOIN image " +
            "ON tattoo.image_id_fk = image.image_id WHERE is_allowed = ?";
    private static final String FIND_BY_ARCHIVED = "SELECT tattoo_id, " +
            "tattoo_name, image_id, image_name FROM tattoo INNER JOIN image " +
            "ON tattoo.image_id_fk = image.image_id WHERE is_archived = ?";
    private static final String FIND_ALL_CATALOG = "SELECT tattoo_id, " +
            "tattoo_name, image_id, image_name FROM tattoo INNER JOIN image " +
            "ON tattoo.image_id_fk = image.image_id " +
            "WHERE is_allowed = 1 AND is_archived = 0";
    private static final String FIND_BY_ID_CATALOG = "SELECT tattoo_id, " +
            "tattoo_name, tattoo_description, tattoo_price, is_allowed, " +
            "is_archived, image_id, image_name FROM tattoo INNER JOIN image " +
            "ON tattoo.image_id_fk = image.image_id " +
            "WHERE tattoo_id = ? AND is_allowed = 1 AND is_archived = 0";
    private static final String FIND_BY_NAME_CATALOG = "SELECT tattoo_id, " +
            "tattoo_name, image_id, image_name FROM tattoo " +
            "INNER JOIN image ON tattoo.image_id_fk = image.image_id " +
            "WHERE tattoo_name LIKE ? AND is_allowed = 1 AND is_archived = 0";
    private static final String PERCENT = "%";

    @Override
    public boolean add(Tattoo tattoo, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(ADD,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, tattoo.getName());
            statement.setString(2, tattoo.getDescription());
            statement.setDouble(3, tattoo.getPrice());
            statement.setLong(4, tattoo.getImage().getImageId());
            statement.setString(5, tattoo.getUser().getLogin());
            boolean result = statement.executeUpdate() > 0;
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                tattoo.setTattooId(generatedKeys.getLong(1));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException("Error while adding tattoo: " + tattoo, e);
        }
    }

    @Override
    public boolean remove(long id, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(REMOVE)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while removing tattoo with id: " + id, e);
        }
    }

    @Override
    public boolean update(Tattoo tattoo) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, tattoo.getName());
            statement.setString(2, tattoo.getDescription());
            statement.setDouble(3, tattoo.getPrice());
            statement.setLong(4, tattoo.getTattooId());
            return statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while updating tattoo: " + tattoo, e);
        }
    }

    @Override
    public boolean offer(Tattoo tattoo, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(OFFER,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, tattoo.getName());
            statement.setString(2, tattoo.getDescription());
            statement.setDouble(3, tattoo.getPrice());
            statement.setLong(4, tattoo.getImage().getImageId());
            statement.setString(5, tattoo.getUser().getLogin());
            boolean result = statement.executeUpdate() > 0;
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                tattoo.setTattooId(generatedKeys.getLong(1));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException("Error while offering tattoo: " + tattoo, e);
        }
    }

    @Override
    public boolean allow(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ALLOW)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while allowing tattoo with id: " + id, e);
        }
    }

    @Override
    public boolean archive(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ARCHIVE)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while archiving tattoo with id: " + id, e);
        }
    }

    @Override
    public boolean unarchive(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(UNARCHIVE)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while unarchiving tattoo " +
                    "with id: " + id, e);
        }
    }

    @Override
    public List<Tattoo> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            List<Tattoo> tattoos = new ArrayList<>();
            while (resultSet.next()) {
                tattoos.add(createPartTattooFromResultSet(resultSet));
            }
            return tattoos;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding all tattoos", e);
        }
    }

    @Override
    public Optional<Tattoo> findById(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<Tattoo> tattooOptional = Optional.empty();
            if (resultSet.next()) {
                tattooOptional =
                        Optional.of(createFullTattooFromResultSet(resultSet));
            }
            return tattooOptional;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Finding tattoos by id error", e);
        }
    }

    @Override
    public List<Tattoo> findByNameSubstring(String nameSubstring) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_NAME)) {
            statement.setString(1,
                    PERCENT + nameSubstring + PERCENT);
            ResultSet resultSet = statement.executeQuery();
            List<Tattoo> tattoos = new ArrayList<>();
            while (resultSet.next()) {
                tattoos.add(createPartTattooFromResultSet(resultSet));
            }
            return tattoos;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding tattoos " +
                    "by name substring: " + nameSubstring, e);
        }
    }

    @Override
    public List<Tattoo> findByAllowed(boolean isAllowed) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_ALLOWED)) {
            statement.setInt(1, isAllowed ? 1 : 0);
            ResultSet resultSet = statement.executeQuery();
            List<Tattoo> tattoos = new ArrayList<>();
            while (resultSet.next()) {
                tattoos.add(createPartTattooFromResultSet(resultSet));
            }
            return tattoos;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding tattoos " +
                    "by allowed: " + isAllowed, e);
        }
    }

    @Override
    public List<Tattoo> findByArchived(boolean isArchived) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_ARCHIVED)) {
            statement.setInt(1, isArchived ? 1 : 0);
            ResultSet resultSet = statement.executeQuery();
            List<Tattoo> tattoos = new ArrayList<>();
            while (resultSet.next()) {
                tattoos.add(createPartTattooFromResultSet(resultSet));
            }
            return tattoos;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding tattoos " +
                    "by archived: " + isArchived, e);
        }
    }

    @Override
    public List<Tattoo> findAllCatalog() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_ALL_CATALOG)) {
            ResultSet resultSet = statement.executeQuery();
            List<Tattoo> tattoos = new ArrayList<>();
            while (resultSet.next()) {
                tattoos.add(createPartTattooFromResultSet(resultSet));
            }
            return tattoos;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding all tattoos in catalog: ", e);
        }
    }

    @Override
    public Optional<Tattoo> findByIdCatalog(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_ID_CATALOG)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<Tattoo> tattooOptional = Optional.empty();
            if (resultSet.next()) {
                tattooOptional =
                        Optional.of(createPartTattooCatalogFromResultSet(resultSet));
            }
            return tattooOptional;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding tattoos in catalog" +
                    "by id: " + id, e);
        }
    }

    @Override
    public List<Tattoo> findByNameSubstringCatalog(String nameSubstring)
            throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_NAME_CATALOG)) {
            statement.setString(1,
                    PERCENT + nameSubstring + PERCENT);
            ResultSet resultSet = statement.executeQuery();
            List<Tattoo> tattoos = new ArrayList<>();
            while (resultSet.next()) {
                tattoos.add(createPartTattooFromResultSet(resultSet));
            }
            return tattoos;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding tattoos in catalog" +
                    "by name substring: " + nameSubstring, e);
        }
    }

    private Tattoo createPartTattooFromResultSet(ResultSet resultSet)
            throws SQLException {
        long tattooId = resultSet.getLong(ColumnName.TATTOO_ID);
        String tattooName = resultSet.getString(ColumnName.TATTOO_NAME);
        long imageId = resultSet.getLong(ColumnName.IMAGE_ID);
        String imageName = resultSet.getString(ColumnName.IMAGE_NAME);
        Image image = new Image(imageId, imageName);
        TattooBuilder tattooBuilder = new TattooBuilder();
        tattooBuilder.setTattooId(tattooId);
        tattooBuilder.setName(tattooName);
        tattooBuilder.setImage(image);
        return tattooBuilder.getTattoo();
    }

    private Tattoo createPartTattooCatalogFromResultSet(ResultSet resultSet)
            throws SQLException {
        long tattooId = resultSet.getLong(ColumnName.TATTOO_ID);
        String tattooName = resultSet.getString(ColumnName.TATTOO_NAME);
        long imageId = resultSet.getLong(ColumnName.IMAGE_ID);
        String imageName = resultSet.getString(ColumnName.IMAGE_NAME);
        String description = resultSet.getString(ColumnName.TATTOO_DESCRIPTION);
        double price = resultSet.getDouble(ColumnName.TATTOO_PRICE);
        Image image = new Image(imageId, imageName);
        TattooBuilder tattooBuilder = new TattooBuilder();
        tattooBuilder.setTattooId(tattooId);
        tattooBuilder.setName(tattooName);
        tattooBuilder.setDescription(description);
        tattooBuilder.setPrice(price);
        tattooBuilder.setImage(image);
        return tattooBuilder.getTattoo();
    }

    private Tattoo createFullTattooFromResultSet(ResultSet resultSet)
            throws SQLException {
        long tattooId = resultSet.getLong(ColumnName.TATTOO_ID);
        String tattooName = resultSet.getString(ColumnName.TATTOO_NAME);
        String description = resultSet.getString(ColumnName.TATTOO_DESCRIPTION);
        double price = resultSet.getDouble(ColumnName.TATTOO_PRICE);
        boolean isAllowed = resultSet.getInt(ColumnName.IS_ALLOWED) != 0;
        boolean isArchived = resultSet.getInt(ColumnName.IS_ARCHIVED) != 0;
        long imageId = resultSet.getLong(ColumnName.IMAGE_ID);
        String imageName = resultSet.getString(ColumnName.IMAGE_NAME);
        String login = resultSet.getString(ColumnName.LOGIN);
        UserBuilder userBuilder = new UserBuilder();
        userBuilder.setLogin(login);
        User user = userBuilder.getUser();
        Image image = new Image(imageId, imageName);
        TattooBuilder tattooBuilder = new TattooBuilder();
        tattooBuilder.setTattooId(tattooId);
        tattooBuilder.setName(tattooName);
        tattooBuilder.setDescription(description);
        tattooBuilder.setPrice(price);
        tattooBuilder.setAllowed(isAllowed);
        tattooBuilder.setArchived(isArchived);
        tattooBuilder.setImage(image);
        tattooBuilder.setUser(user);
        return tattooBuilder.getTattoo();
    }
}
