package com.borikov.bullfinch.service.impl;

import com.borikov.bullfinch.dao.UserDao;
import com.borikov.bullfinch.dao.impl.UserDaoImpl;
import com.borikov.bullfinch.entity.User;
import com.borikov.bullfinch.entity.UserRating;
import com.borikov.bullfinch.entity.UserRole;
import com.borikov.bullfinch.entity.Wallet;
import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.service.UserService;
import com.borikov.bullfinch.util.EmailSender;
import com.borikov.bullfinch.util.PasswordEncryption;
import com.borikov.bullfinch.validator.UserValidator;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Override
    public Optional<User> isUserExists(String login, String password) throws ServiceException {
        try {
            UserValidator userValidator = new UserValidator();
            UserDao userDao = new UserDaoImpl();
            Optional<User> userOptional = Optional.empty();
            if (userValidator.isLoginCorrect(login)
                    && userValidator.isPasswordCorrect(password)) {
                Optional<User> existingUser = userDao.findByLogin(login);
                if (existingUser.isPresent()) {
                    User user = existingUser.get();
                    Optional<String> encryptedPassword = PasswordEncryption.encrypt(password);
                    if (encryptedPassword.isPresent() && user.getLogin().equals(login)
                            && user.getPassword().equals(encryptedPassword.get())) {
                        userOptional = existingUser;
                    }
                }
            }
            return userOptional;
        } catch (DaoException e) {
            throw new ServiceException("Error while checking user for existing", e);
        }
    }

    @Override
    public boolean addUser(String email, String login, String firstName,
                           String secondName, String phoneNumber, String password,
                           String confirmedPassword) throws ServiceException {
        try {
            UserValidator userValidator = new UserValidator();
            UserDao userDao = new UserDaoImpl();
            boolean result = false;
            if (userValidator.isEmailCorrect(email)
                    && userValidator.isLoginCorrect(login)
                    && userValidator.isFirstNameCorrect(firstName)
                    && userValidator.isSecondNameCorrect(secondName)
                    && userValidator.isPhoneCorrect(phoneNumber)
                    && userValidator.isPasswordCorrect(password)
                    && password.equals(confirmedPassword)) {
                Optional<String> encryptedPassword = PasswordEncryption.encrypt(password);
                Optional<User> existingUserLogin = userDao.findByLogin(login);
                Optional<User> existingUserEmail = userDao.findByEmail(email);
                if (existingUserLogin.isEmpty() && existingUserEmail.isEmpty()
                        && encryptedPassword.isPresent()) {
                    User user = new User(null, email, login, encryptedPassword.get(),
                            firstName, secondName, phoneNumber, false, false,
                            UserRole.USER, UserRating.BEGINNER, new Wallet(null, 0));// TODO: 17.09.2020 refactor creating of wallet
                    result = userDao.add(user);
                    EmailSender.sendMessage(user.getEmail(), user.getLogin());
                }
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Error while checking user for existing", e);
        }
    }

    @Override
    public boolean confirmUserEmail(String login) throws ServiceException {
        boolean result = false;
        try {
            UserDao userDao = new UserDaoImpl();
            Optional<User> existingUser = userDao.findByLogin(login);
            if (existingUser.isPresent()) {
                result = userDao.confirmEmail(login);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while confirm email", e);
        }
        return result;
    }
}
