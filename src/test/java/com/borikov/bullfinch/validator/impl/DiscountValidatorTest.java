package com.borikov.bullfinch.validator.impl;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DiscountValidatorTest {
    private DiscountValidator discountValidator;

    @BeforeClass
    public void setUp() {
        discountValidator = new DiscountValidator();
    }

    @AfterClass
    public void tearDown() {
        discountValidator = null;
    }

    @DataProvider(name = "isIdCorrectPositiveData")
    public Object[][] createIsIdCorrectPositiveData() {
        return new Object[][]{
                {"10"},
                {"1"},
                {"3"},
                {"123"},
                {"1234567891"}
        };
    }

    @Test(dataProvider = "isIdCorrectPositiveData")
    public void isIdCorrectPositiveTest(String id) {
        boolean actual = discountValidator.isIdCorrect(id);
        assertTrue(actual);
    }

    @DataProvider(name = "isIdCorrectNegativeData")
    public Object[][] createIsIdCorrectNegativeData() {
        return new Object[][]{
                {"-123"},
                {"-1"},
                {"0"},
                {"-3"},
                {"12345678910"},
                {"   "},
                {null}
        };
    }

    @Test(dataProvider = "isIdCorrectNegativeData")
    public void isIdCorrectNegativeTest(String id) {
        boolean actual = discountValidator.isIdCorrect(id);
        assertFalse(actual);
    }

    @DataProvider(name = "isDiscountPercentCorrectPositiveData")
    public Object[][] createDiscountPercentIdCorrectPositiveData() {
        return new Object[][]{
                {"10"},
                {"1"},
                {"30"},
                {"99"},
                {"50"}
        };
    }

    @Test(dataProvider = "isDiscountPercentCorrectPositiveData")
    public void isDiscountPercentCorrectPositiveTest(String discountPercent) {
        boolean actual = discountValidator.isDiscountPercentCorrect(discountPercent);
        assertTrue(actual);
    }

    @DataProvider(name = "isDiscountPercentCorrectNegativeData")
    public Object[][] createIsDiscountPercentCorrectNegativeData() {
        return new Object[][]{
                {"-123"},
                {"100"},
                {"0"},
                {"50.5"},
                {"12345678910"},
                {"   "},
                {null}
        };
    }

    @Test(dataProvider = "isDiscountPercentCorrectNegativeData")
    public void isDiscountPercentCorrectNegativeTest(String discountPercent) {
        boolean actual = discountValidator.isDiscountPercentCorrect(discountPercent);
        assertFalse(actual);
    }
}