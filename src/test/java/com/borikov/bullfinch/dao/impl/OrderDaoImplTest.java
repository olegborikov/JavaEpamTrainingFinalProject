package com.borikov.bullfinch.dao.impl;

import com.borikov.bullfinch.builder.TattooBuilder;
import com.borikov.bullfinch.builder.UserBuilder;
import com.borikov.bullfinch.dao.OrderDao;
import com.borikov.bullfinch.dao.pool.ConnectionPool;
import com.borikov.bullfinch.entity.Image;
import com.borikov.bullfinch.entity.Order;
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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class OrderDaoImplTest {
    private OrderDao orderDao;
    private Order order1;
    private Order order2;
    private Order order3;
    private Order order4;
    private static final Logger LOGGER = LogManager.getLogger();

    @BeforeClass
    public void setUp() {
        orderDao = OrderDaoImpl.getInstance();
        UserBuilder userBuilder1 = new UserBuilder();
        userBuilder1.setLogin("oleg");
        TattooBuilder tattooBuilder1 = new TattooBuilder();
        tattooBuilder1.setTattooId(2L);
        order1 = new Order(null, 200, LocalDate.now(), "qwe",
                true, userBuilder1.getUser(), tattooBuilder1.getTattoo());
        UserBuilder userBuilder2 = new UserBuilder();
        userBuilder2.setLogin("alex");
        TattooBuilder tattooBuilder2 = new TattooBuilder();
        tattooBuilder2.setTattooId(1L);
        order2 = new Order(null, 300, LocalDate.now(), "ewq",
                false, userBuilder2.getUser(), tattooBuilder2.getTattoo());
        UserBuilder userBuilder3 = new UserBuilder();
        userBuilder3.setLogin("oleg");
        TattooBuilder tattooBuilder3 = new TattooBuilder();
        tattooBuilder3.setTattooId(3L);
        order3 = new Order(null, 300, LocalDate.now(), "ewq",
                false, userBuilder3.getUser(), tattooBuilder3.getTattoo());
        UserBuilder userBuilder4 = new UserBuilder();
        userBuilder4.setLogin("ole");
        TattooBuilder tattooBuilder4 = new TattooBuilder();
        tattooBuilder4.setTattooId(3L);
        order4 = new Order(null, 300, LocalDate.now(), "ewq",
                false, userBuilder4.getUser(), tattooBuilder4.getTattoo());
    }

    @AfterClass
    public void tearDown() {
        orderDao = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM tattoo_order WHERE tattoo_order_id = ?")) {
            statement.setLong(1, order3.getOrderId());
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.log(Level.ERROR, "Error while deleting order", e);
        }
        order1 = null;
        order2 = null;
        order3 = null;
        order4 = null;
    }

    @DataProvider(name = "addPositiveData")
    public Object[][] createAddPositiveData() {
        return new Object[][]{
                {order1},
                {order2},
                {order3}
        };
    }

    @Test(dataProvider = "addPositiveData", priority = 1)
    public void addPositiveTest(Order order) {
        try {
            boolean actual = orderDao.add(order);
            assertTrue(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @DataProvider(name = "addExceptionData")
    public Object[][] createAddExceptionData() {
        return new Object[][]{
                {order4}
        };
    }

    @Test(dataProvider = "addExceptionData",
            expectedExceptions = DaoException.class, priority = 2)
    public void addExceptionTest(Order order) throws DaoException {
        orderDao.add(order);
    }

    @DataProvider(name = "removePositiveData")
    public Object[][] createRemovePositiveData() {
        return new Object[][]{
                {order1.getOrderId()},
                {order2.getOrderId()}
        };
    }

    @Test(dataProvider = "removePositiveData", priority = 3)
    public void removePositiveTest(long id) {
        try {
            boolean actual = orderDao.remove(id);
            assertTrue(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @DataProvider(name = "removeNegativeData")
    public Object[][] createRemoveNegativeData() {
        return new Object[][]{
                {order3.getOrderId() + 1},
                {order3.getOrderId() + 2},
                {order3.getOrderId() + 3}
        };
    }

    @Test(dataProvider = "removeNegativeData", priority = 4)
    public void removeNegativeTest(long id) {
        try {
            boolean actual = orderDao.remove(id);
            assertFalse(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 5)
    public void submitPositiveTest() {
        try {
            boolean actual = orderDao.submit(order3.getOrderId());
            assertTrue(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 6)
    public void submitNegativeTest() {
        try {
            boolean actual = orderDao.submit(order3.getOrderId() + 1);
            assertFalse(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 7)
    public void findByIdPositiveTest() {
        try {
            UserBuilder userBuilder = new UserBuilder();
            userBuilder.setLogin("alex");
            TattooBuilder tattooBuilder = new TattooBuilder();
            tattooBuilder.setName("Girl");
            Image image = new Image(null, "935aeb3c-b0b5-4337-b59e-a2546a8fa94b");
            tattooBuilder.setImage(image);
            Date date = new Date(11111111111111L);
            Order expected = new Order(1L, 500, date.toLocalDate(), "Good",
                    true, userBuilder.getUser(), tattooBuilder.getTattoo());
            Optional<Order> actual = orderDao.findById(1);
            assertEquals(expected, actual.get());
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 8)
    public void findByIdNegativeTest() {
        try {
            UserBuilder userBuilder = new UserBuilder();
            userBuilder.setLogin("alex");
            TattooBuilder tattooBuilder = new TattooBuilder();
            tattooBuilder.setName("Girl");
            Image image = new Image(null, "935aeb3c-b0b5-4337-b59e-a2546a8fa94b");
            tattooBuilder.setImage(image);
            Date date = new Date(0);
            Order expected = new Order(1L, 500, date.toLocalDate(), "Good",
                    true, userBuilder.getUser(), tattooBuilder.getTattoo());
            Optional<Order> actual = orderDao.findById(1);
            assertNotEquals(expected, actual.get());
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 9)
    public void findByUserLoginPositiveTest() {
        try {
            TattooBuilder tattooBuilder1 = new TattooBuilder();
            tattooBuilder1.setName("Girl");
            Date date1 = new Date(11111111111111L);
            Order order5 = new Order(1L, 500, date1.toLocalDate(),
                    null, false, null, tattooBuilder1.getTattoo());
            TattooBuilder tattooBuilder2 = new TattooBuilder();
            tattooBuilder2.setName("Street");
            Date date2 = new Date(11111111111121L);
            Order order6 = new Order(2L, 300, date2.toLocalDate(),
                    null, false, null, tattooBuilder2.getTattoo());
            List<Order> expected = new ArrayList<>();
            expected.add(order5);
            expected.add(order6);
            List<Order> actual = orderDao.findByUserLogin("alex");
            assertEquals(expected, actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 10)
    public void findByUserLoginNegativeTest() {
        try {
            TattooBuilder tattooBuilder1 = new TattooBuilder();
            Image image1 = new Image(null, "935aeb3c-b0b5-4337-b59e-a2546a8fa94b");
            tattooBuilder1.setName("Girl");
            tattooBuilder1.setImage(image1);
            Date date1 = new Date(11111111111111L);
            Order order5 = new Order(1L, 500, date1.toLocalDate(),
                    null, false, null, tattooBuilder1.getTattoo());
            TattooBuilder tattooBuilder2 = new TattooBuilder();
            tattooBuilder2.setName("Street");
            Image image2 = new Image(null, "01cc692c-0e32-4dc2-83bd-ce90eca3768f");
            tattooBuilder2.setImage(image2);
            Date date2 = new Date(11111111111121L);
            Order order6 = new Order(2L, 300, date2.toLocalDate(),
                    null, false, null, tattooBuilder2.getTattoo());
            List<Order> expected = new ArrayList<>();
            expected.add(order5);
            expected.add(order6);
            List<Order> actual = orderDao.findByUserLogin("alex");
            assertNotEquals(expected, actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }
}
