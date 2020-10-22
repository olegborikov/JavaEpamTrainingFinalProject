package com.borikov.bullfinch.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class WalletValidatorTest {
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
        boolean actual = WalletValidator.isIdCorrect(id);
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
        boolean actual = WalletValidator.isIdCorrect(id);
        assertFalse(actual);
    }

    @DataProvider(name = "isEnrichAmountCorrectPositiveData")
    public Object[][] createIsEnrichAmountCorrectPositiveData() {
        return new Object[][]{
                {"10"},
                {"1"},
                {"12345.12"},
                {"32.57"},
                {"33333"}
        };
    }

    @Test(dataProvider = "isEnrichAmountCorrectPositiveData")
    public void isEnrichAmountCorrectPositiveTest(String enrichAmount) {
        boolean actual = WalletValidator.isEnrichAmountCorrect(enrichAmount);
        assertTrue(actual);
    }

    @DataProvider(name = "isEnrichAmountCorrectNegativeData")
    public Object[][] createIsEnrichAmountCorrectNegativeData() {
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

    @Test(dataProvider = "isEnrichAmountCorrectNegativeData")
    public void isEnrichAmountCorrectNegativeTest(String enrichAmount) {
        boolean actual = WalletValidator.isEnrichAmountCorrect(enrichAmount);
        assertFalse(actual);
    }

    @DataProvider(name = "isBalanceCorrectPositiveData")
    public Object[][] createIsBalanceCorrectPositiveData() {
        return new Object[][]{
                {0.01},
                {100},
                {1},
                {12345},
                {99999.99}
        };
    }

    @Test(dataProvider = "isBalanceCorrectPositiveData")
    public void isBalanceCorrectPositiveTest(double balance) {
        boolean actual = WalletValidator.isBalanceCorrect(balance);
        assertTrue(actual);
    }

    @DataProvider(name = "isBalanceCorrectNegativeData")
    public Object[][] createIsBalanceCorrectNegativeData() {
        return new Object[][]{
                {0},
                {0.001},
                {-100},
                {123456},
                {100000}
        };
    }

    @Test(dataProvider = "isBalanceCorrectNegativeData")
    public void isBalanceCorrectNegativeTest(double balance) {
        boolean actual = WalletValidator.isBalanceCorrect(balance);
        assertFalse(actual);
    }
}
