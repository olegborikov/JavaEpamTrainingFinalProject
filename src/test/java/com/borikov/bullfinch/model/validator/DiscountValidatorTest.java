package com.borikov.bullfinch.model.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class DiscountValidatorTest {
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
        boolean actual = DiscountValidator.isIdCorrect(id);
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
        boolean actual = DiscountValidator.isIdCorrect(id);
        assertFalse(actual);
    }

    @DataProvider(name = "isPercentCorrectPositiveData")
    public Object[][] createPercentCorrectPositiveData() {
        return new Object[][]{
                {"10"},
                {"1"},
                {"8"},
                {"30"},
                {"99"},
                {"50"}
        };
    }

    @Test(dataProvider = "isPercentCorrectPositiveData")
    public void isPercentCorrectPositiveTest(String percent) {
        boolean actual = DiscountValidator.isPercentCorrect(percent);
        assertTrue(actual);
    }

    @DataProvider(name = "isPercentCorrectNegativeData")
    public Object[][] createIsPercentCorrectNegativeData() {
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

    @Test(dataProvider = "isPercentCorrectNegativeData")
    public void isPercentCorrectNegativeTest(String percent) {
        boolean actual = DiscountValidator.isPercentCorrect(percent);
        assertFalse(actual);
    }
}
