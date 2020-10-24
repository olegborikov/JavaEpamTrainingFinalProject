package com.borikov.bullfinch.dao.impl;

import com.borikov.bullfinch.builder.TattooBuilder;
import com.borikov.bullfinch.builder.UserBuilder;
import com.borikov.bullfinch.dao.TattooDao;
import com.borikov.bullfinch.dao.pool.ConnectionPool;
import com.borikov.bullfinch.entity.Image;
import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.entity.User;
import com.borikov.bullfinch.exception.ConnectionPoolException;
import com.borikov.bullfinch.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class TattooDaoImplTest {
    private TattooDao tattooDao;
    private Tattoo tattoo1;
    private Tattoo tattoo2;
    private Tattoo tattoo3;
    private Connection connection;
    private static final Logger LOGGER = LogManager.getLogger();

    @BeforeClass
    public void setUp() {
        tattooDao = new TattooDaoImpl();
        TattooBuilder tattooBuilder1 = new TattooBuilder();
        tattooBuilder1.setName("Poa");
        tattooBuilder1.setDescription("Cool");
        tattooBuilder1.setPrice(100);
        tattooBuilder1.setImage(new Image(1L, null));
        UserBuilder userBuilder1 = new UserBuilder();
        userBuilder1.setLogin("oleg");
        User user1 = userBuilder1.getUser();
        tattooBuilder1.setUser(user1);
        tattoo1 = tattooBuilder1.getTattoo();
        TattooBuilder tattooBuilder2 = new TattooBuilder();
        tattooBuilder2.setName("Qwe");
        tattooBuilder2.setDescription("Nice");
        tattooBuilder2.setPrice(100);
        tattooBuilder2.setImage(new Image(1L, null));
        UserBuilder userBuilder2 = new UserBuilder();
        userBuilder2.setLogin("alex");
        User user2 = userBuilder2.getUser();
        tattooBuilder2.setUser(user2);
        tattoo2 = tattooBuilder2.getTattoo();
        TattooBuilder tattooBuilder3 = new TattooBuilder();
        tattooBuilder3.setName("Qwe");
        tattooBuilder3.setDescription("Nice");
        tattooBuilder3.setPrice(100);
        tattooBuilder3.setImage(new Image(1L, null));
        UserBuilder userBuilder3 = new UserBuilder();
        userBuilder3.setLogin("alex");
        User user3 = userBuilder3.getUser();
        tattooBuilder3.setUser(user3);
        tattoo3 = tattooBuilder3.getTattoo();
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
        } catch (ConnectionPoolException e) {
            LOGGER.log(Level.ERROR, "Error while getting connection", e);
        }
    }

    @AfterClass
    public void tearDown() {
        tattooDao = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM tattoo WHERE tattoo_id = ?")) {
            statement.setLong(1, tattoo2.getTattooId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while deleting tattoo", e);
        }
        tattoo1 = null;
        tattoo2 = null;
        tattoo3 = null;
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while closing connection", e);
        }
    }

    @DataProvider(name = "addPositiveData")
    public Object[][] createAddPositiveData() {
        return new Object[][]{
                {tattoo1},
                {tattoo2}
        };
    }

    @Test(dataProvider = "addPositiveData", priority = 1)
    public void addPositiveTest(Tattoo tattoo) {
        try {
            boolean actual = tattooDao.add(tattoo, connection);
            assertTrue(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(expectedExceptions = DaoException.class, priority = 2)
    public void addExceptionTest() throws DaoException {
        TattooBuilder tattooBuilder = new TattooBuilder();
        tattooBuilder.setTattooId(tattoo2.getTattooId() + 1);
        tattooBuilder.setName("Poa");
        tattooBuilder.setDescription("Cool");
        tattooBuilder.setPrice(100);
        tattooBuilder.setImage(new Image(1L, null));
        UserBuilder userBuilder = new UserBuilder();
        userBuilder.setLogin("o");
        User user = userBuilder.getUser();
        tattooBuilder.setUser(user);
        tattooDao.add(tattooBuilder.getTattoo(), connection);
    }

    @Test(priority = 3)
    public void offerPositiveTest() {
        try {
            boolean actual = tattooDao.offer(tattoo3, connection);
            assertTrue(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(expectedExceptions = DaoException.class, priority = 4)
    public void offerExceptionTest() throws DaoException {
        TattooBuilder tattooBuilder = new TattooBuilder();
        tattooBuilder.setTattooId(tattoo2.getTattooId() + 1);
        tattooBuilder.setName("Poa");
        tattooBuilder.setDescription("Cool");
        tattooBuilder.setPrice(100);
        tattooBuilder.setImage(new Image(1L, null));
        UserBuilder userBuilder = new UserBuilder();
        userBuilder.setLogin("o");
        User user = userBuilder.getUser();
        tattooBuilder.setUser(user);
        tattooDao.offer(tattooBuilder.getTattoo(), connection);
    }

    @DataProvider(name = "removePositiveData")
    public Object[][] createRemovePositiveData() {
        return new Object[][]{
                {tattoo1},
                {tattoo3}
        };
    }

    @Test(dataProvider = "removePositiveData", priority = 5)
    public void removePositiveTest(Tattoo tattoo) {
        try {
            boolean actual =
                    tattooDao.remove(tattoo.getTattooId(), connection);
            assertTrue(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 6)
    public void removeNegativeTest() {
        try {
            boolean actual =
                    tattooDao.remove(tattoo2.getTattooId() + 1, connection);
            assertFalse(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 7)
    public void updatePositiveTest() {
        try {
            tattoo2.setName("Qwerty");
            boolean actual = tattooDao.update(tattoo2);
            assertTrue(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 8)
    public void updateNegativeTest() {
        try {
            TattooBuilder tattooBuilder = new TattooBuilder();
            tattooBuilder.setTattooId(tattoo2.getTattooId() + 1);
            tattooBuilder.setName("Poa");
            tattooBuilder.setDescription("Cool");
            tattooBuilder.setPrice(100);
            tattooBuilder.setImage(new Image(1L, null));
            UserBuilder userBuilder = new UserBuilder();
            userBuilder.setLogin("o");
            User user = userBuilder.getUser();
            tattooBuilder.setUser(user);
            boolean actual = tattooDao.update(tattooBuilder.getTattoo());
            assertFalse(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 9)
    public void allowPositiveTest() {
        try {
            boolean actual = tattooDao.allow(tattoo2.getTattooId());
            assertTrue(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 10)
    public void allowNegativeTest() {
        try {
            boolean actual = tattooDao.allow(tattoo2.getTattooId() + 1);
            assertFalse(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 11)
    public void archivePositiveTest() {
        try {
            boolean actual = tattooDao.archive(tattoo2.getTattooId());
            assertTrue(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 12)
    public void archiveNegativeTest() {
        try {
            boolean actual = tattooDao.archive(tattoo2.getTattooId() + 1);
            assertFalse(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 13)
    public void unarchivePositiveTest() {
        try {
            boolean actual =
                    tattooDao.unarchive(tattoo2.getTattooId());
            assertTrue(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 14)
    public void unarchiveNegativeTest() {
        try {
            boolean actual = tattooDao.unarchive(tattoo2.getTattooId() + 1);
            assertFalse(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 15)
    public void findAllPositiveTest() {
        try {
            int expected = 4;
            List<Tattoo> actual = tattooDao.findAll();
            assertEquals(actual.size(), expected);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 16)
    public void findAllNegativeTest() {
        try {
            List<Tattoo> actual = tattooDao.findAll();
            assertFalse(actual.isEmpty());
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 17)
    public void findByIdPositiveTest() {
        try {
            Optional<Tattoo> actual = tattooDao.findById(tattoo2.getTattooId());
            assertTrue(actual.isPresent());
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 18)
    public void findByIdNegativeTest() {
        try {
            Optional<Tattoo> actual =
                    tattooDao.findById(tattoo2.getTattooId() + 1);
            assertFalse(actual.isPresent());
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 19)
    public void findByNameSubstringPositiveTest() {
        try {
            int expected = 2;
            List<Tattoo> actual = tattooDao.findByNameSubstring("i");
            assertEquals(actual.size(), expected);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 20)
    public void findByNameSubstringNegativeTest() {
        try {
            List<Tattoo> actual = tattooDao.findByNameSubstring("i");
            assertFalse(actual.isEmpty());
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 21)
    public void findByAllowedPositiveTest() {
        try {
            int expected = 3;
            List<Tattoo> actual = tattooDao.findByAllowed(true);
            assertEquals(actual.size(), expected);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 22)
    public void findByAllowedNegativeTest() {
        try {
            List<Tattoo> actual = tattooDao.findByAllowed(true);
            assertFalse(actual.isEmpty());
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 23)
    public void findByArchivedPositiveTest() {
        try {
            int expected = 3;
            List<Tattoo> actual = tattooDao.findByArchived(false);
            assertEquals(actual.size(), expected);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 24)
    public void findByArchivedNegativeTest() {
        try {
            List<Tattoo> actual = tattooDao.findByArchived(true);
            assertFalse(actual.isEmpty());
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 25)
    public void findAllCatalogPositiveTest() {
        try {
            int expected = 2;
            List<Tattoo> actual = tattooDao.findAllCatalog();
            assertEquals(actual.size(), expected);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 26)
    public void findAllCatalogNegativeTest() {
        try {
            List<Tattoo> actual = tattooDao.findAllCatalog();
            assertFalse(actual.isEmpty());
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 27)
    public void findByIdCatalogPositiveTest() {
        try {
            Optional<Tattoo> actual = tattooDao.findByIdCatalog(3);
            assertTrue(actual.isEmpty());
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 28)
    public void findByIdCatalogNegativeTest() {
        try {
            Optional<Tattoo> actual =
                    tattooDao.findByIdCatalog(tattoo2.getTattooId());
            assertFalse(actual.isEmpty());
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 29)
    public void findByNameSubstringCatalogPositiveTest() {
        try {
            int expected = 1;
            List<Tattoo> actual = tattooDao.findByNameSubstringCatalog("i");
            assertEquals(actual.size(), expected);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 30)
    public void findByNameSubstringCatalogNegativeTest() {
        try {
            List<Tattoo> actual = tattooDao.findByNameSubstring("i");
            assertFalse(actual.isEmpty());
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }
}
