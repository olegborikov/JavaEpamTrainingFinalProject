package com.borikov.bullfinch.model.service.impl;

import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.exception.TransactionException;
import com.borikov.bullfinch.model.builder.OrderBuilder;
import com.borikov.bullfinch.model.dao.OrderDao;
import com.borikov.bullfinch.model.dao.TransactionManager;
import com.borikov.bullfinch.model.dao.impl.OrderDaoImpl;
import com.borikov.bullfinch.model.entity.Order;
import com.borikov.bullfinch.model.service.OrderService;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class OrderServiceImplTest {
    private OrderDao orderDao;
    private TransactionManager transactionManager;
    private OrderService orderService;

    @BeforeClass
    public void setUp() {
        transactionManager = mock(TransactionManager.class);
        orderDao = mock(OrderDaoImpl.class);
        Whitebox.setInternalState(TransactionManager.class, "INSTANCE", transactionManager);
        Whitebox.setInternalState(OrderDaoImpl.class, "INSTANCE", orderDao);
        orderService = new OrderServiceImpl();
    }

    @AfterClass
    public void tearDown() {
        orderDao = null;
        transactionManager = null;
        orderService = null;
    }

    @Test
    public void addOrderPositiveTest() {
        try {
            when(orderDao.add(any(Order.class))).thenReturn(true);
            boolean actual = orderService.addOrder("2020-10-10", "dsadsa", "100", "1", "oleg", "1");
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void addOrderNegativeTest() {
        try {
            when(orderDao.add(any(Order.class))).thenReturn(true);
            boolean actual = orderService.addOrder("2020-13-10", "dsadsa", "100", "1", "oleg", "1");
            assertFalse(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @DataProvider(name = "removeOrderPositiveData")
    public Object[][] createRemoveOrderPositiveData() {
        return new Object[][]{
                {"100"},
                {"1"},
                {"3"}
        };
    }

    @Test(dataProvider = "removeOrderPositiveData")
    public void removeOrderPositiveTest(String id) {
        try {
            when(orderDao.remove(any(Long.class))).thenReturn(true);
            boolean actual = orderService.removeOrder(id);
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @DataProvider(name = "removeOrderNegativeData")
    public Object[][] createRemoveOrderNegativeData() {
        return new Object[][]{
                {"-100"},
                {"-1"},
                {"0"}
        };
    }

    @Test(dataProvider = "removeOrderNegativeData")
    public void removeOrderNegativeTest(String id) {
        try {
            when(orderDao.remove(any(Long.class))).thenReturn(true);
            boolean actual = orderService.removeOrder(id);
            assertFalse(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @DataProvider(name = "submitOrderPositiveData")
    public Object[][] createSubmitOrderPositiveData() {
        return new Object[][]{
                {"100"},
                {"1"},
                {"3"}
        };
    }

    @Test(dataProvider = "submitOrderPositiveData")
    public void submitOrderPositiveTest(String id) {
        try {
            when(transactionManager.orderSubmitProcess(any(Long.class))).thenReturn(true);
            boolean actual = orderService.submitOrder(id);
            assertTrue(actual);
        } catch (ServiceException | TransactionException e) {
            fail("Incorrect data", e);
        }
    }

    @DataProvider(name = "submitOrderNegativeData")
    public Object[][] createSubmitOrderNegativeData() {
        return new Object[][]{
                {"-100"},
                {"-1"},
                {"0"}
        };
    }

    @Test(dataProvider = "submitOrderNegativeData")
    public void submitOrderNegativeTest(String id) {
        try {
            when(transactionManager.orderSubmitProcess(any(Long.class))).thenReturn(true);
            boolean actual = orderService.submitOrder(id);
            assertFalse(actual);
        } catch (ServiceException | TransactionException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findAllOrdersPositiveTest() {
        try {
            List<Order> expected = new ArrayList<>();
            when(orderDao.findAll()).thenReturn(new ArrayList<>());
            List<Order> actual = orderService.findAllOrders();
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findAllOrdersNegativeTest() {
        try {
            List<Order> expected = null;
            when(orderDao.findAll()).thenReturn(new ArrayList<>());
            List<Order> actual = orderService.findAllOrders();
            assertNotEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findOrderByIdPositiveTest() {
        try {
            Optional<Order> expected = Optional.of(new OrderBuilder().getOrder());
            when(orderDao.findById(any(Long.class))).thenReturn(expected);
            Optional<Order> actual = orderService.findOrderById("1");
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findOrderByIdNegativeTest() {
        try {
            Optional<Order> expected = Optional.of(new OrderBuilder().getOrder());
            when(orderDao.findById(any(Long.class))).thenReturn(expected);
            Optional<Order> actual = orderService.findOrderById("oleg");
            assertNotEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findOrdersByDatesPositiveTest() {
        try {
            List<Order> expected = new ArrayList<>();
            expected.add(new OrderBuilder().getOrder());
            when(orderDao.findByDates(any(LocalDate.class), any(LocalDate.class))).thenReturn(expected);
            List<Order> actual = orderService.findOrdersByDates("2020-10-11", "2020-10-12");
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findOrdersByDatesNegativeTest() {
        try {
            List<Order> expected = new ArrayList<>();
            expected.add(new OrderBuilder().getOrder());
            when(orderDao.findByDates(any(LocalDate.class), any(LocalDate.class))).thenReturn(expected);
            List<Order> actual = orderService.findOrdersByDates("2020-10-11", "2020-10-10");
            assertNotEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findOrdersByUserLoginPositiveTest() {
        try {
            List<Order> expected = new ArrayList<>();
            expected.add(new OrderBuilder().getOrder());
            when(orderDao.findByUserLogin(any(String.class))).thenReturn(expected);
            List<Order> actual = orderService.findOrdersByUserLogin("oleg");
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findOrdersByUserLoginNegativeTest() {
        try {
            List<Order> expected = new ArrayList<>();
            expected.add(new OrderBuilder().getOrder());
            when(orderDao.findByUserLogin(any(String.class))).thenReturn(new ArrayList<>());
            List<Order> actual = orderService.findOrdersByUserLogin(null);
            assertNotEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }
}
