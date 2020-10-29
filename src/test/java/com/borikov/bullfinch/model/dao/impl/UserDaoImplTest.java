package com.borikov.bullfinch.model.dao.impl;

import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.model.builder.UserBuilder;
import com.borikov.bullfinch.model.dao.UserDao;
import com.borikov.bullfinch.model.dao.pool.ConnectionPool;
import com.borikov.bullfinch.model.entity.User;
import com.borikov.bullfinch.model.entity.UserRole;
import com.borikov.bullfinch.model.entity.Wallet;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class UserDaoImplTest {
    private UserDao userDao;
    private User user;
    private Connection connection;
    private static final Logger LOGGER = LogManager.getLogger();

    @BeforeClass
    public void setUp() {
        userDao = UserDaoImpl.getInstance();
        UserBuilder userBuilder = new UserBuilder();
        userBuilder.setLogin("qwerty");
        userBuilder.setEmail("qwerty@email.com");
        userBuilder.setFirstName("Qwerty");
        userBuilder.setSecondName("Qwerty");
        userBuilder.setPhoneNumber("375251111111");
        userBuilder.setWallet(new Wallet(4L, 100));
        userBuilder.setUserRole(UserRole.USER);
        user = userBuilder.getUser();
        connection = ConnectionPool.INSTANCE.getConnection();
    }

    @AfterClass
    public void tearDown() {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM user_account WHERE user_account_id = ?")) {
            statement.setLong(1, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while deleting user: {}", user, e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while closing connection: {} ", connection, e);
        }
        userDao = null;
        user = null;
    }

    @Test(priority = 1)
    public void addPositiveTest() {
        try {
            boolean actual = userDao.add(user, "e43713ad9e2fc4c55c1e2b373d3f548bd1ffed61", connection);
            assertTrue(actual);
        } catch (DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test(expectedExceptions = DaoException.class, priority = 2)
    public void addExceptionTest() throws DaoException {
        boolean actual = userDao.add(user, "123456Aa", connection);
        assertTrue(actual);
    }

    @Test(priority = 3)
    public void updatePositiveTest() {
        try {
            user.setFirstName("Qwerty");
            boolean actual = userDao.update(user);
            assertTrue(actual);
        } catch (DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test(priority = 4)
    public void updateNegativeTest() {
        try {
            UserBuilder userBuilder = new UserBuilder();
            userBuilder.setUserId(user.getUserId() + 1);
            userBuilder.setLogin("qwerty");
            userBuilder.setEmail("qwerty@email.com");
            userBuilder.setFirstName("Qwerty");
            userBuilder.setSecondName("Qwerty");
            userBuilder.setPhoneNumber("375251111111");
            userBuilder.setWallet(new Wallet(4L, 100));
            userBuilder.setUserRole(UserRole.USER);
            boolean actual = userDao.update(userBuilder.getUser());
            assertFalse(actual);
        } catch (DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test(priority = 5)
    public void authorizePositiveTest() {
        try {
            Optional<User> userOptional = userDao.authorize("alex");
            assertTrue(userOptional.isPresent());
        } catch (DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test(priority = 6)
    public void authorizeNegativeTest() {
        try {
            Optional<User> userOptional = userDao.authorize("dada");
            assertFalse(userOptional.isPresent());
        } catch (DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test(priority = 7)
    public void confirmEmailPositiveTest() {
        try {
            boolean actual = userDao.confirmEmail("alex");
            assertTrue(actual);
        } catch (DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test(priority = 8)
    public void confirmEmailNegativeTest() {
        try {
            boolean actual = userDao.confirmEmail("dada");
            assertFalse(actual);
        } catch (DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test(priority = 9)
    public void blockPositiveTest() {
        try {
            boolean actual = userDao.block("oleg");
            assertTrue(actual);
        } catch (DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test(priority = 10)
    public void blockNegativeTest() {
        try {
            boolean actual = userDao.block("dada");
            assertFalse(actual);
        } catch (DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test(priority = 11)
    public void unblockPositiveTest() {
        try {
            boolean actual = userDao.unblock("oleg");
            assertTrue(actual);
        } catch (DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test(priority = 12)
    public void unblockNegativeTest() {
        try {
            boolean actual = userDao.unblock("dada");
            assertFalse(actual);
        } catch (DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test(priority = 13)
    public void checkExistingByLoginPositiveTest() {
        try {
            Optional<String> actual = userDao.checkExistingByLogin("alex");
            assertTrue(actual.isPresent());
        } catch (DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test(priority = 14)
    public void checkExistingByLoginNegativeTest() {
        try {
            Optional<String> actual = userDao.checkExistingByLogin("alex1");
            assertFalse(actual.isPresent());
        } catch (DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test(priority = 15)
    public void checkExistingByEmailPositiveTest() {
        try {
            boolean actual = userDao.checkExistingByEmail("alex@example.com");
            assertTrue(actual);
        } catch (DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test(priority = 16)
    public void checkExistingByEmailNegativeTest() {
        try {
            boolean actual = userDao.checkExistingByEmail("alex1@example.com");
            assertFalse(actual);
        } catch (DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test(priority = 17)
    public void findAllPositiveTest() {
        try {
            List<User> actual = userDao.findAll();
            int expected = 4;
            assertEquals(actual.size(), expected);
        } catch (DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test(priority = 18)
    public void findAllNegativeTest() {
        try {
            List<User> actual = userDao.findAll();
            assertFalse(actual.isEmpty());
        } catch (DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test(priority = 19)
    public void findByLoginPositiveTest() {
        try {
            Optional<User> actual = userDao.findByLogin(user.getLogin());
            assertEquals(actual.get(), user);
        } catch (DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test(priority = 20)
    public void findByLoginNegativeTest() {
        try {
            Optional<User> actual = userDao.findByLogin("oleg");
            assertNotEquals(actual.get(), user);
        } catch (DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test(priority = 21)
    public void findByLoginSubstringPositiveTest() {
        try {
            List<User> actual = userDao.findByLoginSubstring("a");
            int expected = 2;
            assertEquals(actual.size(), expected);
        } catch (DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test(priority = 22)
    public void findByLoginSubstringNegativeTest() {
        try {
            List<User> actual = userDao.findByLoginSubstring("o");
            assertFalse(actual.isEmpty());
        } catch (DaoException e) {
            fail("Incorrect data", e);
        }
    }
}
