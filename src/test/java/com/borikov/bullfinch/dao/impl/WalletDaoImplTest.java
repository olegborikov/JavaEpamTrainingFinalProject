package com.borikov.bullfinch.dao.impl;

import com.borikov.bullfinch.dao.WalletDao;
import com.borikov.bullfinch.dao.pool.ConnectionPool;
import com.borikov.bullfinch.entity.Wallet;
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
import java.util.Optional;

import static org.testng.Assert.*;

public class WalletDaoImplTest {
    private WalletDao walletDao;
    private Wallet wallet1;
    private Wallet wallet2;
    private Wallet wallet3;
    private Connection connection;
    private static final Logger LOGGER = LogManager.getLogger();

    @BeforeClass
    public void setUp() {
        walletDao = new WalletDaoImpl();
        wallet1 = new Wallet(null, 0);
        wallet2 = new Wallet(null, 0);
        wallet3 = new Wallet(null, 0);
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
        } catch (ConnectionPoolException e) {
            LOGGER.log(Level.ERROR, "Error while getting connection", e);
        }
    }

    @AfterClass
    public void tearDown() {
        walletDao = null;
        wallet1 = null;
        wallet2 = null;
        wallet3 = null;
        connection = null;
    }

    @DataProvider(name = "addPositiveData")
    public Object[][] createAddPositiveData() {
        return new Object[][]{
                {wallet1},
                {wallet2},
                {wallet3}
        };
    }

    @Test(dataProvider = "addPositiveData", priority = 1)
    public void addPositiveTest(Wallet wallet) {
        try {
            boolean actual = walletDao.add(wallet, connection);
            assertTrue(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @DataProvider(name = "updatePositiveData")
    public Object[][] createUpdatePositiveData() {
        wallet1.setBalance(1000);
        wallet2.setBalance(500);
        return new Object[][]{
                {wallet1},
                {wallet2}
        };
    }

    @Test(dataProvider = "updatePositiveData", priority = 2)
    public void updatePositiveTest(Wallet wallet) {
        try {
            boolean actual = walletDao.update(wallet);
            assertTrue(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @DataProvider(name = "updateNegativeData")
    public Object[][] createUpdateNegativeData() {
        return new Object[][]{
                {new Wallet(wallet3.getWalletId() + 10, 100)},
                {new Wallet(wallet3.getWalletId() + 20, 30)},
        };
    }

    @Test(dataProvider = "updateNegativeData", priority = 3)
    public void updateNegativeTest(Wallet wallet) {
        try {
            boolean actual = walletDao.update(wallet);
            assertFalse(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 4)
    public void findByIdPositiveTest() {
        try {
            Wallet expected = new Wallet(2L, 1000);
            Optional<Wallet> actual = walletDao.findById(2);
            assertEquals(expected, actual.get());
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 5)
    public void findByIdNegativeTest() {
        try {
            Wallet expected = new Wallet(2L, 1001);
            Optional<Wallet> actual = walletDao.findById(2);
            assertNotEquals(expected, actual.get());
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 6)
    public void findByUserLoginPositiveTest() {
        try {
            Wallet expected = new Wallet(1L, 0);
            Optional<Wallet> actual = walletDao.findByUserLogin("alex");
            assertEquals(expected, actual.get());
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 7)
    public void findByUserLoginNegativeTest() {
        try {
            Wallet expected = new Wallet(3L, 501);
            Optional<Wallet> actual = walletDao.findByUserLogin("oleg");
            assertNotEquals(expected, actual.get());
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 8)
    public void findByOrderIdPositiveTest() {
        try {
            Wallet expected = new Wallet(1L, 0);
            Optional<Wallet> actual = walletDao.findByOrderId(1);
            assertEquals(expected, actual.get());
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 9)
    public void findByOrderIdNegativeTest() {
        try {
            Wallet expected = new Wallet(1L, 1);
            Optional<Wallet> actual = walletDao.findByOrderId(1);
            assertNotEquals(expected, actual.get());
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }
}
