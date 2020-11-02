package com.borikov.bullfinch.model.service.impl;

import com.borikov.bullfinch.model.builder.UserBuilder;
import com.borikov.bullfinch.model.dao.TransactionManager;
import com.borikov.bullfinch.model.dao.UserDao;
import com.borikov.bullfinch.model.dao.impl.UserDaoImpl;
import com.borikov.bullfinch.model.entity.User;
import com.borikov.bullfinch.model.entity.UserRole;
import com.borikov.bullfinch.model.entity.Wallet;
import com.borikov.bullfinch.model.exception.DaoException;
import com.borikov.bullfinch.model.exception.ServiceException;
import com.borikov.bullfinch.model.exception.TransactionException;
import com.borikov.bullfinch.model.service.UserService;
import com.borikov.bullfinch.model.validator.UserValidator;
import com.borikov.bullfinch.util.PasswordEncryptor;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final TransactionManager transactionManager = TransactionManager.getInstance();
    private final UserDao userDao = UserDaoImpl.getInstance();

    @Override
    public boolean addUser(String email, String login, String firstName, String secondName, String phoneNumber,
                           String password, String confirmedPassword) throws ServiceException {
        try {
            boolean result = false;
            if (UserValidator.isEmailCorrect(email) && UserValidator.isLoginCorrect(login)
                    && UserValidator.isFirstNameCorrect(firstName) && UserValidator.isSecondNameCorrect(secondName)
                    && UserValidator.isPhoneNumberCorrect(phoneNumber) && UserValidator.isPasswordCorrect(password)
                    && password.equals(confirmedPassword)) {
                Optional<String> encryptedPassword = PasswordEncryptor.encrypt(password);
                Optional<String> existingUserPassword = userDao.checkExistingByLogin(login);
                boolean existingUserEmail = userDao.checkExistingByEmail(email);
                if (existingUserPassword.isEmpty() && !existingUserEmail && encryptedPassword.isPresent()) {
                    UserBuilder userBuilder = new UserBuilder();
                    userBuilder.setEmail(email);
                    userBuilder.setLogin(login);
                    userBuilder.setFirstName(firstName);
                    userBuilder.setSecondName(secondName);
                    userBuilder.setPhoneNumber(phoneNumber);
                    userBuilder.setUserRole(UserRole.USER);
                    userBuilder.setWallet(new Wallet(null, 0));
                    User user = userBuilder.getUser();
                    result = transactionManager.addWalletAndUser(user, encryptedPassword.get());
                }
            }
            return result;
        } catch (DaoException | TransactionException e) {
            throw new ServiceException("Error while adding user: email = " + email
                    + ", login = " + login + ", first name = " + firstName + ", second name = "
                    + secondName + ", phone number = " + phoneNumber, e);
        }
    }

    @Override
    public boolean editUser(String id, String email, String login, String firstName, String secondName,
                            String phoneNumber) throws ServiceException {
        try {
            boolean result = false;
            if (UserValidator.isIdCorrect(id) && UserValidator.isEmailCorrect(email)
                    && UserValidator.isLoginCorrect(login) && UserValidator.isFirstNameCorrect(firstName)
                    && UserValidator.isSecondNameCorrect(secondName)
                    && UserValidator.isPhoneNumberCorrect(phoneNumber)) {
                UserBuilder userBuilder = new UserBuilder();
                long userId = Long.parseLong(id);
                userBuilder.setUserId(userId);
                userBuilder.setEmail(email);
                userBuilder.setLogin(login);
                userBuilder.setFirstName(firstName);
                userBuilder.setSecondName(secondName);
                userBuilder.setPhoneNumber(phoneNumber);
                User user = userBuilder.getUser();
                result = userDao.update(user);
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Error while editing user: id = " + id + ", email = " + email
                    + ", login = " + login + ", first name = " + firstName + ", second name = "
                    + secondName + ", phone number = " + phoneNumber, e);
        }
    }

    @Override
    public boolean confirmUserEmail(String login) throws ServiceException {
        try {
            boolean result = false;
            if (UserValidator.isLoginCorrect(login)) {
                result = userDao.confirmEmail(login);
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean blockUser(String login) throws ServiceException {
        try {
            boolean result = false;
            if (UserValidator.isLoginCorrect(login)) {
                result = userDao.block(login);
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean unblockUser(String login) throws ServiceException {
        try {
            boolean result = false;
            if (UserValidator.isLoginCorrect(login)) {
                result = userDao.unblock(login);
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> authorizeUser(String login, String password) throws ServiceException {
        try {
            Optional<User> userOptional = Optional.empty();
            if (UserValidator.isLoginCorrect(login) && UserValidator.isPasswordCorrect(password)) {
                Optional<String> userPasswordOptional = userDao.checkExistingByLogin(login);
                if (userPasswordOptional.isPresent()) {
                    String userPassword = userPasswordOptional.get();
                    Optional<String> encryptedPassword = PasswordEncryptor.encrypt(password);
                    if (encryptedPassword.isPresent() && userPassword.equals(encryptedPassword.get())) {
                        userOptional = userDao.authorize(login);
                    }
                }
            }
            return userOptional;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws ServiceException {
        try {
            Optional<User> user = Optional.empty();
            if (UserValidator.isLoginCorrect(login)) {
                user = userDao.findByLogin(login);
            }
            return user;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findUsersByLoginSubstring(String loginSubstring) throws ServiceException {
        try {
            return userDao.findByLoginSubstring(loginSubstring);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
