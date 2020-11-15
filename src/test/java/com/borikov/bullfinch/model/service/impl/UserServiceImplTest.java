package com.borikov.bullfinch.model.service.impl;

import com.borikov.bullfinch.model.builder.UserBuilder;
import com.borikov.bullfinch.model.dao.TransactionManager;
import com.borikov.bullfinch.model.dao.UserDao;
import com.borikov.bullfinch.model.dao.impl.UserDaoImpl;
import com.borikov.bullfinch.model.entity.User;
import com.borikov.bullfinch.model.exception.DaoException;
import com.borikov.bullfinch.model.exception.ServiceException;
import com.borikov.bullfinch.model.exception.TransactionException;
import com.borikov.bullfinch.model.service.UserService;
import com.borikov.bullfinch.util.RegistrationParameter;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class UserServiceImplTest {
    private UserDao userDao;
    private TransactionManager transactionManager;
    private UserService userService;

    @BeforeClass
    public void setUp() {
        userDao = mock(UserDaoImpl.class);
        transactionManager = mock(TransactionManager.class);
        Whitebox.setInternalState(UserDaoImpl.class, "instance", userDao);
        Whitebox.setInternalState(TransactionManager.class, "instance", transactionManager);
        userService = new UserServiceImpl();
    }

    @AfterClass
    public void tearDown() {
        userDao = null;
        transactionManager = null;
        userService = null;
    }

    @Test
    public void addUserPositiveTest() {
        try {
            when(userDao.checkExistingByLogin(any(String.class))).thenReturn(Optional.empty());
            when(userDao.checkExistingByEmail(any(String.class))).thenReturn(true);
            when(transactionManager.addWalletAndUser(any(User.class), any(String.class))).thenReturn(true);
            Map<String, String> actual = new HashMap<>();
            actual.put(RegistrationParameter.EMAIL, "example@gmail.com");
            actual.put(RegistrationParameter.LOGIN, "oleg");
            actual.put(RegistrationParameter.FIRST_NAME, "qwe");
            actual.put(RegistrationParameter.SECOND_NAME, "qwe");
            actual.put(RegistrationParameter.PHONE_NUMBER, "375251111111");
            actual.put(RegistrationParameter.PASSWORD, "123456Aa");
            actual.put(RegistrationParameter.CONFIRMED_PASSWORD, "123456Aa");
            actual.put(RegistrationParameter.LOGIN_EXISTS, "oleg");
            actual.put(RegistrationParameter.EMAIL_EXISTS, "example@gmail.com");
            Map<String, String> expected = new HashMap<>();
            expected.put(RegistrationParameter.EMAIL, "example@gmail.com");
            expected.put(RegistrationParameter.LOGIN, "oleg");
            expected.put(RegistrationParameter.FIRST_NAME, "qwe");
            expected.put(RegistrationParameter.SECOND_NAME, "qwe");
            expected.put(RegistrationParameter.PHONE_NUMBER, "375251111111");
            expected.put(RegistrationParameter.PASSWORD, "123456Aa");
            expected.put(RegistrationParameter.CONFIRMED_PASSWORD, "123456Aa");
            expected.put(RegistrationParameter.LOGIN_EXISTS, "oleg");
            expected.put(RegistrationParameter.EMAIL_EXISTS, "");
            userService.addUser(actual);
            assertEquals(actual, expected);
        } catch (ServiceException | TransactionException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void addUserNegativeTest() {
        try {
            when(userDao.checkExistingByLogin(any(String.class))).thenReturn(Optional.empty());
            when(userDao.checkExistingByEmail(any(String.class))).thenReturn(false);
            when(transactionManager.addWalletAndUser(any(User.class), any(String.class))).thenReturn(true);
            Map<String, String> actual = new HashMap<>();
            actual.put(RegistrationParameter.EMAIL, "example@gmail.com");
            actual.put(RegistrationParameter.LOGIN, "oleg");
            actual.put(RegistrationParameter.FIRST_NAME, "qwe");
            actual.put(RegistrationParameter.SECOND_NAME, "qwe");
            actual.put(RegistrationParameter.PHONE_NUMBER, "375251111111");
            actual.put(RegistrationParameter.PASSWORD, "123456Aa");
            actual.put(RegistrationParameter.CONFIRMED_PASSWORD, "123456Aa");
            actual.put(RegistrationParameter.LOGIN_EXISTS, "oleg");
            actual.put(RegistrationParameter.EMAIL_EXISTS, "example@gmail.com");
            Map<String, String> expected = new HashMap<>();
            expected.put(RegistrationParameter.EMAIL, "example@gmail.com");
            expected.put(RegistrationParameter.LOGIN, "oleg");
            expected.put(RegistrationParameter.FIRST_NAME, "qwe");
            expected.put(RegistrationParameter.SECOND_NAME, "qwe");
            expected.put(RegistrationParameter.PHONE_NUMBER, "375251111111");
            expected.put(RegistrationParameter.PASSWORD, "123456Aa");
            expected.put(RegistrationParameter.CONFIRMED_PASSWORD, "123456Aa");
            expected.put(RegistrationParameter.LOGIN_EXISTS, "oleg");
            expected.put(RegistrationParameter.EMAIL_EXISTS, "");
            userService.addUser(actual);
            assertNotEquals(actual, expected);
        } catch (ServiceException | TransactionException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void editUserPositiveTest() {
        try {
            when(userDao.update(any(User.class))).thenReturn(true);
            boolean actual = userService.editUser("1", "oleg@gmail.com", "oleg", "oleg", "black", "375251111111");
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void editUserNegativeTest() {
        try {
            when(userDao.update(any(User.class))).thenReturn(true);
            boolean actual = userService.editUser("1", "oleg@gmail.com", "oleg", "oleg", "black", "");
            assertFalse(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void confirmUserEmailPositiveTest() {
        try {
            when(userDao.confirmEmail(any(String.class))).thenReturn(true);
            boolean actual = userService.confirmUserEmail("oleg");
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void confirmUserEmailNegativeTest() {
        try {
            when(userDao.confirmEmail(any(String.class))).thenReturn(true);
            boolean actual = userService.confirmUserEmail(" ");
            assertFalse(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void blockUserPositiveTest() {
        try {
            when(userDao.block(any(String.class))).thenReturn(true);
            boolean actual = userService.blockUser("oleg");
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void blockUserNegativeTest() {
        try {
            when(userDao.block(any(String.class))).thenReturn(true);
            boolean actual = userService.blockUser(" ");
            assertFalse(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void unblockUserPositiveTest() {
        try {
            when(userDao.unblock(any(String.class))).thenReturn(true);
            boolean actual = userService.unblockUser("oleg");
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void unblockUserNegativeTest() {
        try {
            when(userDao.unblock(any(String.class))).thenReturn(true);
            boolean actual = userService.unblockUser(" ");
            assertFalse(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void authorizeUserPositiveTest() {
        try {
            User expected = new UserBuilder().buildUser();
            when(userDao.checkExistingByLogin(any(String.class)))
                    .thenReturn(Optional.of("3b648288c16bba6bf7b6dbceb1c179a7e6ce26cc"));
            when(userDao.authorize(any(String.class))).thenReturn(Optional.of(new UserBuilder().buildUser()));
            Optional<User> actual = userService.authorizeUser("oleg", "1234567Aa");
            assertEquals(actual.get(), expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void authorizeUserNegativeTest() {
        try {
            Optional<User> expected = Optional.of(new UserBuilder().buildUser());
            when(userDao.checkExistingByLogin(any(String.class)))
                    .thenReturn(Optional.of("3b648288c16bba6bf7b6dbceb1c179a7e6ce26cc"));
            when(userDao.authorize(any(String.class))).thenReturn(Optional.of(new UserBuilder().buildUser()));
            Optional<User> actual = userService.authorizeUser("oleg", "123456Aa");
            assertNotEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findAllUsersPositiveTest() {
        try {
            List<User> expected = new ArrayList<>();
            when(userDao.findAll()).thenReturn(new ArrayList<>());
            List<User> actual = userService.findAllUsers();
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findAllUsersNegativeTest() {
        try {
            List<User> expected = null;
            when(userDao.findAll()).thenReturn(new ArrayList<>());
            List<User> actual = userService.findAllUsers();
            assertNotEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findUserByLoginPositiveTest() {
        try {
            User expected = new UserBuilder().buildUser();
            when(userDao.findByLogin(any(String.class))).thenReturn(Optional.of(new UserBuilder().buildUser()));
            Optional<User> actual = userService.findUserByLogin("oleg");
            assertEquals(actual.get(), expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findUserByLoginNegativeTest() {
        try {
            Optional<User> expected = Optional.of(new UserBuilder().buildUser());
            when(userDao.findByLogin(any(String.class))).thenReturn(Optional.of(new UserBuilder().buildUser()));
            Optional<User> actual = userService.findUserByLogin(" ");
            assertNotEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findUsersByLoginSubstringPositiveTest() {
        try {
            List<User> expected = new ArrayList<>();
            when(userDao.findByLoginSubstring(any(String.class))).thenReturn(new ArrayList<>());
            List<User> actual = userService.findUsersByLoginSubstring("a");
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findUsersByLoginSubstringNegativeTest() {
        try {
            List<User> expected = null;
            when(userDao.findByLoginSubstring(any(String.class))).thenReturn(null);
            List<User> actual = userService.findUsersByLoginSubstring("<");
            assertNotEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }
}
