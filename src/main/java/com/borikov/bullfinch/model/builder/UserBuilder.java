package com.borikov.bullfinch.model.builder;

import com.borikov.bullfinch.model.entity.User;
import com.borikov.bullfinch.model.entity.UserRole;
import com.borikov.bullfinch.model.entity.Wallet;

/**
 * The {@code UserBuilder} class represents user builder.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class UserBuilder {
    private Long userId;
    private String email;
    private String login;
    private String firstName;
    private String secondName;
    private String phoneNumber;
    private boolean isBlocked;
    private boolean isActivated;
    private UserRole userRole;
    private Wallet wallet;

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets second name.
     *
     * @param secondName the second name
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets blocked.
     *
     * @param blocked the blocked
     */
    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    /**
     * Sets activated.
     *
     * @param activated the activated
     */
    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    /**
     * Sets user role.
     *
     * @param userRole the user role
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    /**
     * Sets wallet.
     *
     * @param wallet the wallet
     */
    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return new User(userId, email, login, firstName, secondName,
                phoneNumber, isBlocked, isActivated, userRole, wallet);
    }
}
