package com.borikov.bullfinch.dao.impl;

import com.borikov.bullfinch.builder.UserBuilder;
import com.borikov.bullfinch.dao.DiscountDao;
import com.borikov.bullfinch.entity.Discount;
import com.borikov.bullfinch.exception.DaoException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class DiscountDaoImplTest {
    private DiscountDao discountDao;
    private Discount discount1;
    private Discount discount2;
    private Discount discount3;
    private Discount discount4;

    @BeforeClass
    public void setUp() {
        discountDao = DiscountDaoImpl.getInstance();
        UserBuilder userBuilder1 = new UserBuilder();
        userBuilder1.setUserId(2L);
        discount1 = new Discount(null, 50, userBuilder1.getUser());
        UserBuilder userBuilder2 = new UserBuilder();
        userBuilder2.setUserId(3L);
        discount2 = new Discount(null, 10, userBuilder2.getUser());
        UserBuilder userBuilder3 = new UserBuilder();
        userBuilder3.setUserId(4L);
        discount3 = new Discount(null, 30, userBuilder3.getUser());
        UserBuilder userBuilder4 = new UserBuilder();
        userBuilder4.setUserId(4L);
        discount4 = new Discount(null, 1000, userBuilder4.getUser());
    }

    @AfterClass
    public void tearDown() {
        discountDao = null;
        discount1 = null;
        discount2 = null;
        discount3 = null;
        discount4 = null;
    }

    @DataProvider(name = "addPositiveData")
    public Object[][] createAddPositiveData() {
        return new Object[][]{
                {discount1},
                {discount2},
                {discount3}
        };
    }

    @Test(dataProvider = "addPositiveData", priority = 1)
    public void addPositiveTest(Discount discount) {
        try {
            boolean actual = discountDao.add(discount);
            assertTrue(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @DataProvider(name = "addExceptionData")
    public Object[][] createAddExceptionData() {
        return new Object[][]{
                {discount4}
        };
    }

    @Test(dataProvider = "addExceptionData", expectedExceptions = DaoException.class, priority = 2)
    public void addExceptionTest(Discount discount) throws DaoException {
        discountDao.add(discount);
    }

    @DataProvider(name = "removePositiveData")
    public Object[][] createRemovePositiveData() {
        return new Object[][]{
                {discount1.getDiscountId()},
                {discount2.getDiscountId()},
                {discount3.getDiscountId()}
        };
    }

    @Test(dataProvider = "removePositiveData", priority = 3)
    public void removePositiveTest(long id) {
        try {
            boolean actual = discountDao.remove(id);
            assertTrue(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @DataProvider(name = "removeNegativeData")
    public Object[][] createRemoveNegativeData() {
        return new Object[][]{
                {discount3.getDiscountId() + 1},
                {discount3.getDiscountId() + 1},
                {discount3.getDiscountId() + 1}
        };
    }

    @Test(dataProvider = "removeNegativeData", priority = 4)
    public void removeNegativeTest(long id) {
        try {
            boolean actual = discountDao.remove(id);
            assertFalse(actual);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 5)
    public void findByUserLoginPositiveTest() {
        try {
            Discount discount5 = new Discount(1L, 50, null);
            Discount discount6 = new Discount(2L, 10, null);
            List<Discount> expected = new ArrayList<>();
            expected.add(discount5);
            expected.add(discount6);
            List<Discount> actual = discountDao.findByUserLogin("alex");
            assertEquals(actual, expected);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }

    @Test(priority = 6)
    public void findByUserLoginNegativeTest() {
        try {
            Discount discount5 = new Discount(1L, 50, null);
            Discount discount6 = new Discount(2L, 10, null);
            List<Discount> expected = new ArrayList<>();
            expected.add(discount5);
            expected.add(discount6);
            List<Discount> actual = discountDao.findByUserLogin("ale");
            assertNotEquals(actual, expected);
        } catch (DaoException e) {
            fail("incorrect data", e);
        }
    }
}
