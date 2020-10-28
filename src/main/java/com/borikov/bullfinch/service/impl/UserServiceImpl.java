package com.borikov.bullfinch.service.impl;

import com.borikov.bullfinch.builder.UserBuilder;
import com.borikov.bullfinch.dao.TransactionManager;
import com.borikov.bullfinch.dao.UserDao;
import com.borikov.bullfinch.dao.impl.UserDaoImpl;
import com.borikov.bullfinch.entity.User;
import com.borikov.bullfinch.entity.UserRole;
import com.borikov.bullfinch.entity.Wallet;
import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.exception.TransactionException;
import com.borikov.bullfinch.service.UserService;
import com.borikov.bullfinch.util.PasswordEncryptor;
import com.borikov.bullfinch.validator.UserValidator;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final TransactionManager transactionManager = new TransactionManager();
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
            throw new ServiceException("Error while adding user", e);
        }
    }

    @Override
    public boolean editUser(String id, String email, String login, String firstName, String secondName,
                            String phoneNumber) throws ServiceException {
        boolean result = false;
        try {
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
        } catch (DaoException e) {
            throw new ServiceException("Error while editing user", e);
        }
        return result;
    }

    @Override
    public boolean confirmUserEmail(String login) throws ServiceException {
        boolean result = false;
        try {
            Optional<User> existingUser = userDao.findByLogin(login);
            if (existingUser.isPresent()) {
                result = userDao.confirmEmail(login);
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Error while confirming email", e);
        }
    }

    @Override
    public boolean blockUser(String login) throws ServiceException {
        boolean result = false;
        try {
            if (UserValidator.isLoginCorrect(login)) {
                result = userDao.block(login);
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Error while blocking user", e);
        }
    }

    @Override
    public boolean unblockUser(String login) throws ServiceException {
        boolean result = false;
        try {
            if (UserValidator.isLoginCorrect(login)) {
                result = userDao.unblock(login);
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Error while unblocking user", e);
        }
    }

    @Override
    public Optional<User> isUserExists(String login, String password) throws ServiceException {
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
            throw new ServiceException("Error while checking user for existing", e);
        }
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        try {
            List<User> users = userDao.findAll();
            return users;
        } catch (DaoException e) {
            throw new ServiceException("Error while finding users", e);
        }
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws ServiceException {
        Optional<User> user = Optional.empty();
        try {
            if (UserValidator.isLoginCorrect(login)) {
                user = userDao.findByLogin(login);
            }
            return user;
        } catch (DaoException e) {
            throw new ServiceException("Error while finding user by login", e);
        }
    }

    @Override
    public List<User> findUsersByLogin(String login) throws ServiceException {
        try {
            return userDao.findByLoginSubstring(login);
        } catch (DaoException e) {
            throw new ServiceException("Error while finding users by login", e);
        }
    }
}
