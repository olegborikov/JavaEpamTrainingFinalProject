package com.borikov.bullfinch.util;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class XssSecurityTest {
    @DataProvider(name = "securePositiveData")
    public Object[][] createSecurePositiveData() {
        return new Object[][]{
                {"Cool", "Cool"},
                {"<script>", ""},
                {"<script><script></script>", ""},
                {"<script>hello</script>", "hello"},
                {"", ""},
                {null, null},
        };
    }

    @Test(dataProvider = "securePositiveData")
    public void securePositiveTest(String password, String expected) {
        String actual = XssSecurity.secure(password);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "secureNegativeData")
    public Object[][] createSecureNegativeData() {
        return new Object[][]{
                {"Cool", ""},
                {"<script>", " "},
                {"<script><script></script>", "<script>"},
                {"<script>hello</script>", "<script>hello"},
                {"", " "},
                {null, ""},
        };
    }

    @Test(dataProvider = "secureNegativeData")
    public void secureNegativeTest(String password, String expected) {
        String actual = XssSecurity.secure(password);
        assertNotEquals(actual, expected);
    }
}
