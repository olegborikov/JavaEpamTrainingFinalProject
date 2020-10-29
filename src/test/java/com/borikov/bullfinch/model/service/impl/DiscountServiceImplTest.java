package com.borikov.bullfinch.model.service.impl;

import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.model.builder.UserBuilder;
import com.borikov.bullfinch.model.dao.DiscountDao;
import com.borikov.bullfinch.model.dao.impl.DiscountDaoImpl;
import com.borikov.bullfinch.model.entity.Discount;
import com.borikov.bullfinch.model.service.DiscountService;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class DiscountServiceImplTest {
    private DiscountDao discountDao;
    private DiscountService discountService;

    @BeforeClass
    public void setUp() {
        discountDao = mock(DiscountDaoImpl.class);
        Whitebox.setInternalState(DiscountDaoImpl.class, "INSTANCE", discountDao);
        discountService = new DiscountServiceImpl();
    }

    @AfterClass
    public void tearDown() {
        discountDao = null;
        discountService = null;
    }

    @DataProvider(name = "addDiscountPositiveData")
    public Object[][] createAddDiscountPositiveData() {
        return new Object[][]{
                {"90", "2"},
                {"70", "1"},
                {"30", "3"}
        };
    }

    @Test(dataProvider = "addDiscountPositiveData")
    public void addDiscountPositiveTest(String discountPercent, String userId) {
        try {
            when(discountDao.add(any(Discount.class))).thenReturn(true);
            boolean actual = discountService.addDiscount(discountPercent, userId);
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @DataProvider(name = "addDiscountNegativeData")
    public Object[][] createAddDiscountNegativeData() {
        return new Object[][]{
                {"100", "2"},
                {"70", "-1"},
                {"30", "0"}
        };
    }

    @Test(dataProvider = "addDiscountNegativeData")
    public void addDiscountNegativeTest(String discountPercent, String userId) {
        try {
            when(discountDao.add(any(Discount.class))).thenReturn(true);
            boolean actual = discountService.addDiscount(discountPercent, userId);
            assertFalse(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @DataProvider(name = "removeDiscountPositiveData")
    public Object[][] createRemoveDiscountPositiveData() {
        return new Object[][]{
                {"100"},
                {"1"},
                {"6"}
        };
    }

    @Test(dataProvider = "removeDiscountPositiveData")
    public void removeDiscountPositiveTest(String id) {
        try {
            when(discountDao.remove(any(Long.class))).thenReturn(true);
            boolean actual = discountService.removeDiscount(id);
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @DataProvider(name = "removeDiscountNegativeData")
    public Object[][] createRemoveDiscountNegativeData() {
        return new Object[][]{
                {"-100"},
                {"-1"},
                {"0"}
        };
    }

    @Test(dataProvider = "removeDiscountNegativeData")
    public void removeDiscountNegativeTest(String id) {
        try {
            when(discountDao.remove(any(Long.class))).thenReturn(true);
            boolean actual = discountService.removeDiscount(id);
            assertFalse(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findDiscountsByUserLoginPositiveTest() {
        try {
            List<Discount> expected = new ArrayList<>();
            expected.add(new Discount(1L, 10, new UserBuilder().getUser()));
            when(discountDao.findByUserLogin(any(String.class))).thenReturn(expected);
            List<Discount> actual = discountService.findDiscountsByUserLogin("oleg");
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findDiscountsByUserLoginNegativeTest() {
        try {
            List<Discount> expected = new ArrayList<>();
            expected.add(new Discount(1L, 10, new UserBuilder().getUser()));
            when(discountDao.findByUserLogin(any(String.class))).thenReturn(expected);
            List<Discount> actual = discountService.findDiscountsByUserLogin(null);
            assertNotEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }
}
