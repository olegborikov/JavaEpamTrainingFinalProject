package com.borikov.bullfinch.util;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.*;

public class PasswordEncryptorTest {
    @DataProvider(name = "encryptPositiveData")
    public Object[][] createEncryptPositiveData() {
        return new Object[][]{
                {"1234567Aa", "3b648288c16bba6bf7b6dbceb1c179a7e6ce26cc"},
                {"1234567Oo", "8850ca1c4a1d9c67d76b6e2673e73af057d5961b"},
                {"1234", "7110eda4d09e062aa5e4a390b0a572ac0d2c0220"},
                {"qwerty", "b1b3773a05c0ed0176787a4f1574ff0075f7521e"},
        };
    }

    @Test(dataProvider = "encryptPositiveData")
    public void encryptPositiveTest(String password, String expected) {
        Optional<String> actual = PasswordEncryptor.encrypt(password);
        assertEquals(actual.get(), expected);
    }

    @DataProvider(name = "encryptNegativeData")
    public Object[][] createEncryptNegativeData() {
        return new Object[][]{
                {"1234567A", "3b648288c16bba6bf7b6dbceb1c179a7e6ce26cc"},
                {"", "8850ca1c4a1d9c67d76b6e2673e73af057d5961b"},
                {"1234", "7110eda4d09e062aa5e4a390b0a572ac0d2c022"},
                {"qwerty", "b1b3773a05c0ed0176787a4f1574ff0075f7521ee"},
        };
    }

    @Test(dataProvider = "encryptNegativeData")
    public void encryptNegativeTest(String password, String expected) {
        Optional<String> actual = PasswordEncryptor.encrypt(password);
        assertNotEquals(actual.get(), expected);
    }
}
