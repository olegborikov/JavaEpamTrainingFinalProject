package com.borikov.bullfinch.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class UserValidatorTest {
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
        boolean actual = UserValidator.isIdCorrect(id);
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
        boolean actual = UserValidator.isIdCorrect(id);
        assertFalse(actual);
    }

    @DataProvider(name = "isEmailCorrectPositiveData")
    public Object[][] createIsEmailCorrectPositiveData() {
        return new Object[][]{
                {"qwe@qwe.qwe"},
                {".fdsfsd.231ds.@fsdf.fds.F.qwe"},
                {"fds+fds-fds.@YanDEX.com"},
                {"+++@qwe3.qwe"},
                {"dsa_fds@qwe.qwe"},
                {"dsa--fds@qwe.qwe"}
        };
    }

    @Test(dataProvider = "isEmailCorrectPositiveData")
    public void isEmailCorrectPositiveTest(String email) {
        boolean result = UserValidator.isEmailCorrect(email);
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
                {"dsa_fds?@qqweqweqweqwewe.qwe"},
                {""},
                {"   "},
                {null}
        };
    }

    @Test(dataProvider = "isEmailCorrectNegativeData")
    public void isEmailCorrectNegativeTest(String email) {
        boolean result = UserValidator.isEmailCorrect(email);
        assertFalse(result);
    }

    @DataProvider(name = "isLoginCorrectPositiveData")
    public Object[][] createIsLoginCorrectPositiveData() {
        return new Object[][]{
                {"oleg"},
                {"qwe_qwe.."},
                {"..."},
                {"__oleg__"},
                {"o12"},
                {"oleg.borikov"}
        };
    }

    @Test(dataProvider = "isLoginCorrectPositiveData")
    public void isLoginCorrectPositiveTest(String login) {
        boolean result = UserValidator.isLoginCorrect(login);
        assertTrue(result);
    }

    @DataProvider(name = "isLoginCorrectNegativeData")
    public Object[][] createIsLoginCorrectNegativeData() {
        return new Object[][]{
                {"olegolegolegolegoelgoleg"},
                {"qwe_qwe..?"},
                {"qw"},
                {"#fsdfsdf"},
                {"1"},
                {""},
                {"   "},
                {null}
        };
    }

    @Test(dataProvider = "isLoginCorrectNegativeData")
    public void isLoginCorrectNegativeTest(String login) {
        boolean result = UserValidator.isLoginCorrect(login);
        assertFalse(result);
    }

    @DataProvider(name = "isPasswordCorrectPositiveData")
    public Object[][] createIsPasswordCorrectPositiveData() {
        return new Object[][]{
                {"123Qq5678"},
                {"qweQ2eqwe"},
                {"Admin123456"},
                {"123456Oo"},
        };
    }

    @Test(dataProvider = "isPasswordCorrectPositiveData")
    public void isPasswordCorrectPositiveTest(String password) {
        boolean result = UserValidator.isPasswordCorrect(password);
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
                {"qweQwertyev"},
                {""},
                {"   "},
                {null}
        };
    }

    @Test(dataProvider = "isPasswordCorrectNegativeData")
    public void isPasswordCorrectNegativeTest(String password) {
        boolean result = UserValidator.isPasswordCorrect(password);
        assertFalse(result);
    }

    @DataProvider(name = "isFirstNameCorrectPositiveData")
    public Object[][] createIsFirstNameCorrectPositiveData() {
        return new Object[][]{
                {"Al"},
                {"Саша"},
                {"Oleg"},
                {"Aleksaндр"}
        };
    }

    @Test(dataProvider = "isFirstNameCorrectPositiveData")
    public void isFirstNameCorrectPositiveTest(String firstName) {
        boolean result = UserValidator.isFirstNameCorrect(firstName);
        assertTrue(result);
    }

    @DataProvider(name = "isFirstNameCorrectNegativeData")
    public Object[][] createIsFirstNameCorrectNegativeData() {
        return new Object[][]{
                {"Oleg1"},
                {"Олег?"},
                {"Alex Black"},
                {"Alex_Black"},
                {"12345678"},
                {"qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"},
                {"q"},
                {""},
                {"   "},
                {null}
        };
    }

    @Test(dataProvider = "isFirstNameCorrectNegativeData")
    public void isFirstNameCorrectNegativeTest(String firstName) {
        boolean result = UserValidator.isFirstNameCorrect(firstName);
        assertFalse(result);
    }

    @DataProvider(name = "isSecondNameCorrectPositiveData")
    public Object[][] createIsSecondNameCorrectPositiveData() {
        return new Object[][]{
                {"Al"},
                {"Саша"},
                {"Oleg"},
                {"Aleksaндр"}
        };
    }

    @Test(dataProvider = "isSecondNameCorrectPositiveData")
    public void isSecondNameCorrectPositiveTest(String secondName) {
        boolean result = UserValidator.isSecondNameCorrect(secondName);
        assertTrue(result);
    }

    @DataProvider(name = "isSecondNameCorrectNegativeData")
    public Object[][] createIsSecondNameCorrectNegativeData() {
        return new Object[][]{
                {"Oleg1"},
                {"Олег?"},
                {"Alex Black"},
                {"Alex_Black"},
                {"12345678"},
                {"qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"},
                {"q"},
                {""},
                {"   "},
                {null}
        };
    }

    @Test(dataProvider = "isSecondNameCorrectNegativeData")
    public void isSecondNameCorrectNegativeTest(String secondName) {
        boolean result = UserValidator.isSecondNameCorrect(secondName);
        assertFalse(result);
    }

    @DataProvider(name = "isPhoneNumberCorrectPositiveData")
    public Object[][] createIsPhoneNumberCorrectPositiveData() {
        return new Object[][]{
                {"+375251111111"},
                {"80291111111"},
                {"375241111111"},
                {"+375331111111"},
                {"80441111111"},
        };
    }

    @Test(dataProvider = "isPhoneNumberCorrectPositiveData")
    public void isPhoneNumberCorrectPositiveTest(String phoneNumber) {
        boolean result = UserValidator.isPhoneNumberCorrect(phoneNumber);
        assertTrue(result);
    }

    @DataProvider(name = "isPhoneNumberCorrectNegativeData")
    public Object[][] createIsPhoneNumberCorrectNegativeData() {
        return new Object[][]{
                {"37525111111"},
                {"70291111111"},
                {"375211111111"},
                {"+3753311111111"},
                {"800441111111"},
        };
    }

    @Test(dataProvider = "isPhoneNumberCorrectNegativeData")
    public void isPhoneNumberCorrectNegativeTest(String phoneNumber) {
        boolean result = UserValidator.isPhoneNumberCorrect(phoneNumber);
        assertFalse(result);
    }
}
