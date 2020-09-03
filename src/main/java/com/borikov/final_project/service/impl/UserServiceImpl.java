package com.borikov.final_project.service.impl;

import com.borikov.final_project.dao.UserDao;
import com.borikov.final_project.dao.impl.UserDaoImpl;
import com.borikov.final_project.entity.User;
import com.borikov.final_project.exception.DaoException;
import com.borikov.final_project.exception.ServiceException;
import com.borikov.final_project.service.UserService;
import com.borikov.final_project.validator.UserValidator;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Override
    public boolean isUserExists(String login, String password) throws ServiceException {
        try {
            UserValidator userValidator = new UserValidator();
            UserDao userDao = new UserDaoImpl();
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            boolean result = false;
            if (userValidator.isLoginCorrect(login)
                    && userValidator.isPasswordCorrect(password)) {
                Optional<User> userOptional = userDao.findByLogin(login);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
                    byte[] passwordEncodedBytes = messageDigest.digest();
                    BigInteger passwordBigInt = new BigInteger(1, passwordEncodedBytes);
                    String passwordEncrypted = passwordBigInt.toString(16);
                    result = user.getLogin().equals(login)
                            && user.getPassword().equals(passwordEncrypted);
                }
            }
            return result;
        } catch (DaoException | NoSuchAlgorithmException e) {
            throw new ServiceException("Error while checking user for existing", e);
        }
    }

    @Override
    public boolean addUser(String login, String password) throws ServiceException {
        try {
            UserValidator userValidator = new UserValidator();
            UserDao userDao = new UserDaoImpl();
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            boolean result = false;
            if (userValidator.isLoginCorrect(login)
                    && userValidator.isPasswordCorrect(password)) {
                messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
                byte[] passwordEncodedBytes = messageDigest.digest();
                BigInteger passwordBigInt = new BigInteger(1, passwordEncodedBytes);
                String passwordEncrypted = passwordBigInt.toString(16);
                User user = new User(null, login, passwordEncrypted);
                result = userDao.add(user);
            }
            return result;
        } catch (DaoException | NoSuchAlgorithmException e) {
            throw new ServiceException("Error while checking user for existing", e);
        }
    }
}
