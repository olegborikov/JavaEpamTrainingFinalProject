package com.borikov.bullfinch.model.service.impl;

import com.borikov.bullfinch.model.exception.DaoException;
import com.borikov.bullfinch.model.exception.ServiceException;
import com.borikov.bullfinch.model.builder.OrderBuilder;
import com.borikov.bullfinch.model.dao.OrderDao;
import com.borikov.bullfinch.model.dao.WalletDao;
import com.borikov.bullfinch.model.dao.impl.OrderDaoImpl;
import com.borikov.bullfinch.model.dao.impl.WalletDaoImpl;
import com.borikov.bullfinch.model.entity.Wallet;
import com.borikov.bullfinch.model.service.WalletService;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class WalletServiceImplTest {
    private WalletDao walletDao;
    private OrderDao orderDao;
    private WalletService walletService;

    @BeforeClass
    public void setUp() {
        walletDao = mock(WalletDaoImpl.class);
        orderDao = mock(OrderDaoImpl.class);
        Whitebox.setInternalState(WalletDaoImpl.class, "INSTANCE", walletDao);
        Whitebox.setInternalState(OrderDaoImpl.class, "INSTANCE", orderDao);
        walletService = new WalletServiceImpl();
    }

    @AfterClass
    public void tearDown() {
        walletDao = null;
        walletService = null;
    }

    @DataProvider(name = "enrichBalancePositiveData")
    public Object[][] createEnrichBalancePositiveData() {
        return new Object[][]{
                {"100", "100"},
                {"1", "1000"},
                {"5", "500"}
        };
    }

    @Test(dataProvider = "enrichBalancePositiveData")
    public void enrichBalancePositiveTest(String walletId, String enrichAmount) {
        try {
            when(walletDao.findById(any(Long.class))).thenReturn(Optional.of(new Wallet(1L, 500)));
            when(walletDao.update(any(Wallet.class))).thenReturn(true);
            boolean actual = walletService.enrichBalance(walletId, enrichAmount);
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @DataProvider(name = "enrichBalanceNegativeData")
    public Object[][] createEnrichBalanceNegativeData() {
        return new Object[][]{
                {"-1", "100"},
                {"1", "100000"},
                {"0", "500"},
                {"5", "99600"}
        };
    }

    @Test(dataProvider = "enrichBalanceNegativeData")
    public void enrichBalanceNegativeTest(String walletId, String enrichAmount) {
        try {
            when(walletDao.findById(any(Long.class))).thenReturn(Optional.of(new Wallet(1L, 500)));
            when(walletDao.update(any(Wallet.class))).thenReturn(true);
            boolean actual = walletService.enrichBalance(walletId, enrichAmount);
            assertFalse(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void checkBalanceSizeUserLoginPositiveTest() {
        try {
            when(walletDao.findByUserLogin(any(String.class))).thenReturn(Optional.of(new Wallet(1L, 500)));
            boolean actual = walletService.checkBalanceSize("oleg", "100");
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @DataProvider(name = "checkBalanceSizeUserLoginNegativeData")
    public Object[][] createCheckBalanceSizeUserLoginNegativeData() {
        return new Object[][]{
                {"oleg", "600"},
                {"1", "100000"},
                {"ol", "500"}
        };
    }

    @Test(dataProvider = "checkBalanceSizeUserLoginNegativeData")
    public void checkBalanceSizeUserLoginNegativeTest(String userLogin, String price) {
        try {
            when(walletDao.findByUserLogin(any(String.class))).thenReturn(Optional.of(new Wallet(1L, 500)));
            boolean actual = walletService.checkBalanceSize(userLogin, price);
            assertFalse(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void checkBalanceSizeOrderIdPositiveTest() {
        try {
            when(walletDao.findByOrderId(any(Long.class))).thenReturn(Optional.of(new Wallet(1L, 500)));
            OrderBuilder orderBuilder = new OrderBuilder();
            orderBuilder.setPrice(500);
            when(orderDao.findById(any(Long.class))).thenReturn(Optional.of(orderBuilder.getOrder()));
            boolean actual = walletService.checkBalanceSize("1");
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void checkBalanceSizeOrderIdNegativeTest() {
        try {
            when(walletDao.findByOrderId(any(Long.class))).thenReturn(Optional.of(new Wallet(1L, 500)));
            OrderBuilder orderBuilder = new OrderBuilder();
            orderBuilder.setPrice(501);
            when(orderDao.findById(any(Long.class))).thenReturn(Optional.of(orderBuilder.getOrder()));
            boolean actual = walletService.checkBalanceSize("1");
            assertFalse(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }
}
