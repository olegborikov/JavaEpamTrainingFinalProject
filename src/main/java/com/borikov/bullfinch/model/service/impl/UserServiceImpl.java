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
import com.borikov.bullfinch.util.RegistrationParameter;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The {@code UserServiceImpl} class represents user service implementation.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class UserServiceImpl implements UserService {
    private static final String EMPTY_VALUE = "";
    private final TransactionManager transactionManager = TransactionManager.getInstance();
    private final UserDao userDao = UserDaoImpl.getInstance();

    @Override
    public boolean addUser(Map<String, String> registrationParameters) throws ServiceException {
        try {
            boolean isUserAdded = false;
            if (UserValidator.isRegistrationParametersCorrect(registrationParameters)) {
                Optional<String> existingUserPassword =
                        userDao.checkExistingByLogin(registrationParameters.get(RegistrationParameter.LOGIN));
                if (existingUserPassword.isEmpty()) {
                    boolean existingUserEmail =
                            userDao.checkExistingByEmail(registrationParameters.get(RegistrationParameter.EMAIL));
                    if (!existingUserEmail) {
                        Optional<String> encryptedPassword =
                                PasswordEncryptor.encrypt(registrationParameters.get(RegistrationParameter.PASSWORD));
                        if (encryptedPassword.isPresent()) {
                            UserBuilder userBuilder = new UserBuilder();
                            userBuilder.setEmail(registrationParameters.get(RegistrationParameter.EMAIL));
                            userBuilder.setLogin(registrationParameters.get(RegistrationParameter.LOGIN));
                            userBuilder.setFirstName(registrationParameters.get(RegistrationParameter.FIRST_NAME));
                            userBuilder.setSecondName(registrationParameters.get(RegistrationParameter.SECOND_NAME));
                            userBuilder.setPhoneNumber(registrationParameters.get(RegistrationParameter.PHONE_NUMBER));
                            userBuilder.setUserRole(UserRole.USER);
                            userBuilder.setWallet(new Wallet(null, 0));
                            User user = userBuilder.getUser();
                            isUserAdded = transactionManager.addWalletAndUser(user, encryptedPassword.get());
                        }
                    } else {
                        registrationParameters.put(RegistrationParameter.EMAIL_EXISTS, EMPTY_VALUE);
                    }
                } else {
                    registrationParameters.put(RegistrationParameter.LOGIN_EXISTS, EMPTY_VALUE);
                }
            }
            return isUserAdded;
        } catch (DaoException | TransactionException e) {
            StringBuilder message = new StringBuilder("Error while adding user: ");
            message.append("email = ").append(registrationParameters.get(RegistrationParameter.EMAIL));
            message.append(", login = ").append(registrationParameters.get(RegistrationParameter.LOGIN));
            message.append(", first name = ").append(registrationParameters.get(RegistrationParameter.FIRST_NAME));
            message.append(", second name = ").append(registrationParameters.get(RegistrationParameter.SECOND_NAME));
            message.append(", phone number = ").append(registrationParameters.get(RegistrationParameter.PHONE_NUMBER));
            throw new ServiceException(message.toString(), e);
        }
    }

    @Override
    public boolean editUser(String id, String email, String login, String firstName, String secondName,
                            String phoneNumber) throws ServiceException {
        try {
            boolean isUserEdited = false;
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
                isUserEdited = userDao.update(user);
            }
            return isUserEdited;
        } catch (DaoException e) {
            StringBuilder message = new StringBuilder("Error while editing user: ");
            message.append("id = ").append(id);
            message.append(", email = ").append(email);
            message.append(", login = ").append(login);
            message.append(", first name = ").append(firstName);
            message.append(", second name = ").append(secondName);
            message.append(", phone number = ").append(phoneNumber);
            throw new ServiceException(message.toString(), e);
        }
    }

    @Override
    public boolean confirmUserEmail(String login) throws ServiceException {
        try {
            boolean isUserEmailConfirmed = false;
            if (UserValidator.isLoginCorrect(login)) {
                isUserEmailConfirmed = userDao.confirmEmail(login);
            }
            return isUserEmailConfirmed;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean blockUser(String login) throws ServiceException {
        try {
            boolean isUserBlocked = false;
            if (UserValidator.isLoginCorrect(login)) {
                isUserBlocked = userDao.block(login);
            }
            return isUserBlocked;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean unblockUser(String login) throws ServiceException {
        try {
            boolean isUserUnblocked = false;
            if (UserValidator.isLoginCorrect(login)) {
                isUserUnblocked = userDao.unblock(login);
            }
            return isUserUnblocked;
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
