package com.borikov.bullfinch.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class OrderValidatorTest {
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
        boolean actual = OrderValidator.isIdCorrect(id);
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
        boolean actual = OrderValidator.isIdCorrect(id);
        assertFalse(actual);
    }

    @DataProvider(name = "isPriceCorrectPositiveData")
    public Object[][] createIsPriceCorrectPositiveData() {
        return new Object[][]{
                {"10"},
                {"1"},
                {"12345.12"},
                {"32.57"},
                {"33333"}
        };
    }

    @Test(dataProvider = "isPriceCorrectPositiveData")
    public void isPriceCorrectPositiveTest(String price) {
        boolean actual = OrderValidator.isPriceCorrect(price);
        assertTrue(actual);
    }

    @DataProvider(name = "isPriceCorrectNegativeData")
    public Object[][] createIsPriceCorrectNegativeData() {
        return new Object[][]{
                {"-10"},
                {"0"},
                {"123456.12"},
                {"32.573"},
                {"333333"},
                {"   "},
                {null}
        };
    }

    @Test(dataProvider = "isPriceCorrectNegativeData")
    public void isPriceCorrectNegativeTest(String price) {
        boolean actual = OrderValidator.isPriceCorrect(price);
        assertFalse(actual);
    }

    @DataProvider(name = "isDateCorrectPositiveData")
    public Object[][] createIsDateCorrectPositiveData() {
        return new Object[][]{
                {"2020-01-10"},
                {"2000-11-12"},
                {"2040-12-31"},
                {"2011-04-30"},
                {"2079-11-29"},
        };
    }

    @Test(dataProvider = "isDateCorrectPositiveData")
    public void isDateCorrectPositiveTest(String date) {
        boolean actual = OrderValidator.isDateCorrect(date);
        assertTrue(actual);
    }

    @DataProvider(name = "isDateCorrectNegativeData")
    public Object[][] createIsDateCorrectNegativeData() {
        return new Object[][]{
                {"1999-01-10"},
                {"2000-13-12"},
                {"2040-12-32"},
                {"2100-11-29"},
                {"   "},
                {null}
        };
    }

    @Test(dataProvider = "isDateCorrectNegativeData")
    public void isDateCorrectNegativeTest(String date) {
        boolean actual = OrderValidator.isDateCorrect(date);
        assertFalse(actual);
    }

    @DataProvider(name = "isDescriptionCorrectPositiveData")
    public Object[][] createIsDescriptionCorrectPositiveData() {
        return new Object[][]{
                {"hello, i want to take it"},
                {"goodbue"},
                {"..."},
                {"12345678910"}
        };
    }

    @Test(dataProvider = "isDescriptionCorrectPositiveData")
    public void isDescriptionCorrectPositiveTest(String description) {
        boolean actual = OrderValidator.isDescriptionCorrect(description);
        assertTrue(actual);
    }

    @DataProvider(name = "isDescriptionCorrectNegativeData")
    public Object[][] createIsDescriptionCorrectNegativeData() {
        return new Object[][]{
                {""},
                {"hello< goodbye"},
                {"goodbye<script>alert</script>"},
                {"          <script>alert</script>"},
                {"<script>alert</script>"},
                {"   "},
                {null}
        };
    }

    @Test(dataProvider = "isDescriptionCorrectNegativeData")
    public void isDescriptionCorrectNegativeTest(String description) {
        boolean actual = OrderValidator.isDescriptionCorrect(description);
        assertFalse(actual);
    }
}
