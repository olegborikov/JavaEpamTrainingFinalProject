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
import com.borikov.bullfinch.util.EmailSenderUtil;
import com.borikov.bullfinch.util.PasswordEncryptor;
import com.borikov.bullfinch.validator.UserValidator;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public Optional<User> isUserExists(String login, String password) throws ServiceException {
        try {
            UserValidator userValidator = new UserValidator();
            Optional<User> userOptional = Optional.empty();
            if (userValidator.isLoginCorrect(login)
                    && userValidator.isPasswordCorrect(password)) {
                Optional<String> userPasswordOptional = userDao.checkExistingByLogin(login);
                if (userPasswordOptional.isPresent()) {
                    String userPassword = userPasswordOptional.get();
                    Optional<String> encryptedPassword = PasswordEncryptor.encrypt(password);
                    if (encryptedPassword.isPresent()
                            && userPassword.equals(encryptedPassword.get())) {
                        userOptional = userDao.findByLogin(login);
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
            boolean result = false;
            if (userValidator.isEmailCorrect(email)
                    && userValidator.isLoginCorrect(login)
                    && userValidator.isFirstNameCorrect(firstName)
                    && userValidator.isSecondNameCorrect(secondName)
                    && userValidator.isPhoneCorrect(phoneNumber)
                    && userValidator.isPasswordCorrect(password)
                    && password.equals(confirmedPassword)) {
                Optional<String> encryptedPassword = PasswordEncryptor.encrypt(password);
                Optional<String> existingUserPassword = userDao.checkExistingByLogin(login);// TODO: 20.09.2020 is it correct to get password of random user in registration
                boolean existingUserEmail = userDao.checkExistingByEmail(email);
                if (existingUserPassword.isEmpty() && !existingUserEmail
                        && encryptedPassword.isPresent()) {
                    User user = new User(null, email, login,
                            firstName, secondName, phoneNumber, false, false,
                            UserRole.USER, UserRating.BEGINNER, new Wallet(null, 0));// TODO: 17.09.2020 refactor creating of wallet
                    result = userDao.add(user, encryptedPassword.get());
                    EmailSenderUtil.sendMessage(user.getEmail(), user.getLogin());
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
