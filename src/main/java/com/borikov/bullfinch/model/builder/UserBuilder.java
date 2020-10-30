package com.borikov.bullfinch.model.builder;

import com.borikov.bullfinch.model.entity.User;
import com.borikov.bullfinch.model.entity.UserRole;
import com.borikov.bullfinch.model.entity.Wallet;

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

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public User getUser() {
        return new User(userId, email, login, firstName, secondName,
                phoneNumber, isBlocked, isActivated, userRole, wallet);
    }
}