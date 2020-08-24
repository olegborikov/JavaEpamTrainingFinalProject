package com.borikov.final_project.validator;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserValidatorTest {
    private UserValidator userValidator;

    @BeforeClass
    public void setUp() {
        userValidator = new UserValidator();
    }

    @AfterClass
    public void tearDown() {
        userValidator = null;
    }

    @DataProvider(name = "isEmailCorrectPositiveData")
    public Object[][] createIsEmailCorrectPositiveData() {
        return new Object[][]{
                {"qwe@qwe.qwe"},
                {".fdsfsd.231ds.@fsdf.fds.F.qwe"},
                {"fds+fds-fds.@YanDEX.com"},
                {"+++@qwe.qwe"},
                {"dsa_fds@qwe.qwe"},
                {"dsa--fds@qwe.qwe"}
        };
    }

    @Test(dataProvider = "isEmailCorrectPositiveData")
    public void isEmailCorrectPositiveTest(String email) {
        boolean result = userValidator.isEmailCorrect(email);
        assertTrue(result);
    }

    @DataProvider(name = "isEmailCorrectNegativeData")
    public Object[][] createIsEmailCorrectNegativeData() {
        return new Object[][]{
                {"qwe@qwe.aaaaaa"},
                {".fdsfsd.231ds.@f.qwe"},
                {"3@YanDEX.com"},
                {"@qwe.qwe"},
                {"dsa_fds?@qwe.qwe"},
                {"dsa_fds?@qqweqweqweqwewe.qwe"}
        };
    }

    @Test(dataProvider = "isEmailCorrectNegativeData")
    public void isEmailCorrectNegativeTest(String email) {
        boolean result = userValidator.isEmailCorrect(email);
        assertFalse(result);
    }

    @DataProvider(name = "isPasswordCorrectPositiveData")
    public Object[][] createIsPasswordCorrectPositiveData() {
        return new Object[][]{
                {"123Qq5678"},
                {"qweQ2eqwe"},
                {"Admin123456"}
        };
    }

    @Test(dataProvider = "isPasswordCorrectPositiveData")
    public void isPasswordCorrectPositiveTest(String password) {
        boolean result = userValidator.isPasswordCorrect(password);
        assertTrue(result);
    }

    @DataProvider(name = "isPasswordCorrectNegativeData")
    public Object[][] createIsPasswordCorrectNegativeData() {
        return new Object[][]{
                {"123Qq57"},
                {"qweQ2eqweqweQ2eqweqweQ2eqwe"},
                {"123Qq5678?"},
                {"123Qq5678_"},
                {"12345678"},
                {"qwertyui2"},
                {"123Q123433"},
                {"qweQwertyev"}
        };
    }

    @Test(dataProvider = "isPasswordCorrectNegativeData")
    public void isPasswordCorrectNegativeTest(String password) {
        boolean result = userValidator.isPasswordCorrect(password);
        assertFalse(result);
    }
}
