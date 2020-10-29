package com.borikov.bullfinch.model.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TattooValidatorTest {
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
        boolean actual = TattooValidator.isIdCorrect(id);
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
        boolean actual = TattooValidator.isIdCorrect(id);
        assertFalse(actual);
    }

    @DataProvider(name = "isNameCorrectPositiveData")
    public Object[][] createIsNameCorrectPositiveData() {
        return new Object[][]{
                {"hello, i want to take it"},
                {"goodbue"},
                {"..."},
                {"12345678910"}
        };
    }

    @Test(dataProvider = "isNameCorrectPositiveData")
    public void isNameCorrectPositiveTest(String name) {
        boolean actual = TattooValidator.isNameCorrect(name);
        assertTrue(actual);
    }

    @DataProvider(name = "isNameCorrectNegativeData")
    public Object[][] createIsNameCorrectNegativeData() {
        return new Object[][]{
                {""},
                {"<dads"},
                {"hello< goodbye"},
                {"qqqqqqqqqqqqqqqqqqqqqqqqqqqqq"},
                {"<script>alert</script>"},
                {"   "},
                {null}
        };
    }

    @Test(dataProvider = "isNameCorrectNegativeData")
    public void isNameCorrectNegativeTest(String name) {
        boolean actual = TattooValidator.isNameCorrect(name);
        assertFalse(actual);
    }

    @DataProvider(name = "isDescriptionCorrectPositiveData")
    public Object[][] createIsDescriptionCorrectPositiveData() {
        return new Object[][]{
                {"hello, i want to take it"},
                {"goodbye"},
                {"..."},
                {"12345678910"}
        };
    }

    @Test(dataProvider = "isDescriptionCorrectPositiveData")
    public void isDescriptionCorrectPositiveTest(String description) {
        boolean actual = TattooValidator.isDescriptionCorrect(description);
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
        boolean actual = TattooValidator.isDescriptionCorrect(description);
        assertFalse(actual);
    }

    @DataProvider(name = "isPriceCorrectPositiveData")
    public Object[][] createIsPriceCorrectPositiveData() {
        return new Object[][]{
                {"10"},
                {"1"},
                {"12345.12"},
                {"32.57"},
                {"33333"},
        };
    }

    @Test(dataProvider = "isPriceCorrectPositiveData")
    public void isPriceCorrectPositiveTest(String price) {
        boolean actual = TattooValidator.isPriceCorrect(price);
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
        boolean actual = TattooValidator.isPriceCorrect(price);
        assertFalse(actual);
    }
}
