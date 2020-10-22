package com.borikov.bullfinch.dao.impl;

import com.borikov.bullfinch.dao.ImageDao;
import com.borikov.bullfinch.dao.pool.ConnectionPool;
import com.borikov.bullfinch.entity.Image;
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
import java.sql.SQLException;

import static org.testng.Assert.*;

public class ImageDaoImplTest {
    private ImageDao imageDao;
    private Image image1;
    private Image image2;
    private Image image3;
    private Image image4;
    Connection connection;
    private static final Logger LOGGER = LogManager.getLogger();

    @BeforeClass
    public void setUp() {
        imageDao = new ImageDaoImpl();
        image1 = new Image(null, "01cc692c-0e32-4dc2-83bd-ce90eca11111");
        image2 = new Image(null, "01cc692c-0e32-4dc2-83bd-ce90eca11112");
        image3 = new Image(null, "01cc692c-0e32-4dc2-83bd-ce90eca11110");
        image4 = new Image(null, "01cc692c-0e32-4dc2-83bd-ce90eca11110233432");
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
        } catch (ConnectionPoolException e) {
            LOGGER.log(Level.ERROR, "Error while getting connection", e);
        }
    }

    @AfterClass
    public void tearDown() {
        imageDao = null;
        image1 = null;
        image2 = null;
        image3 = null;
        image4 = null;
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Error while closing connection", e);
            }
        }
    }

    @DataProvider(name = "addPositiveData")
    public Object[][] createAddPositiveData() {
        return new Object[][]{
                {image1, connection},
                {image2, connection},
                {image3, connection}
        };
    }

    @Test(dataProvider = "addPositiveData", priority = 1)
    public void addPositiveTest(Image image, Connection connection) {
        try {
            boolean actual = imageDao.add(image, connection);
            assertTrue(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @DataProvider(name = "addExceptionData")
    public Object[][] createAddExceptionData() {
        return new Object[][]{
                {image4, connection}
        };
    }

    @Test(dataProvider = "addExceptionData",
            expectedExceptions = DaoException.class, priority = 2)
    public void addExceptionTest(Image image, Connection connection)
            throws DaoException {
        imageDao.add(image, connection);
    }

    @DataProvider(name = "removePositiveData")
    public Object[][] createRemovePositiveData() {
        return new Object[][]{
                {image1.getImageId(), connection},
                {image2.getImageId(), connection},
                {image3.getImageId(), connection}
        };
    }

    @Test(dataProvider = "removePositiveData", priority = 3)
    public void removePositiveTest(long id, Connection connection) {
        try {
            boolean actual = imageDao.remove(id, connection);
            assertTrue(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @DataProvider(name = "removeNegativeData")
    public Object[][] createRemoveNegativeData() {
        return new Object[][]{
                {image3.getImageId() + 1, connection},
                {image3.getImageId() + 2, connection},
                {image3.getImageId() + 3, connection}
        };
    }

    @Test(dataProvider = "removeNegativeData", priority = 4)
    public void removeNegativeTest(long id, Connection connection) {
        try {
            boolean actual = imageDao.remove(id, connection);
            assertFalse(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }
}