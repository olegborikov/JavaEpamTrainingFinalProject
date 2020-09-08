package com.borikov.bullfinch.service.impl;

import com.borikov.bullfinch.dao.UserDao;
import com.borikov.bullfinch.dao.impl.UserDaoImpl;
import com.borikov.bullfinch.entity.User;
import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.service.UserService;
import com.borikov.bullfinch.util.PasswordEncryption;
import com.borikov.bullfinch.validator.UserValidator;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Override
    public boolean isUserExists(String login, String password) throws ServiceException {
        try {
            UserValidator userValidator = new UserValidator();
            UserDao userDao = new UserDaoImpl();
            boolean result = false;
            if (userValidator.isLoginCorrect(login)
                    && userValidator.isPasswordCorrect(password)) {
                Optional<User> userOptional = userDao.findByLogin(login);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    Optional<String> encryptedPassword = PasswordEncryption.encrypt(password);
                    if (encryptedPassword.isPresent()) {
                        result = user.getLogin().equals(login)
                                && user.getPassword().equals(encryptedPassword.get());
                    }
                }
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Error while checking user for existing", e);
        }
    }

    @Override
    public boolean addUser(String login, String password) throws ServiceException {
        try {
            UserValidator userValidator = new UserValidator();
            UserDao userDao = new UserDaoImpl();
            boolean result = false;
            if (userValidator.isLoginCorrect(login)
                    && userValidator.isPasswordCorrect(password)) {
                Optional<String> encryptedPassword = PasswordEncryption.encrypt(password);
                Optional<User> existingUser = userDao.findByLogin(login);
                if (existingUser.isEmpty() && encryptedPassword.isPresent()) {
                    User user = new User(null, login, encryptedPassword.get());
                    result = userDao.add(user);
                }
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Error while checking user for existing", e);
        }
    }
}
