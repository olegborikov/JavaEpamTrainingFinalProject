package com.borikov.bullfinch.builder.impl;

import com.borikov.bullfinch.builder.UserBuilder;
import com.borikov.bullfinch.entity.User;
import com.borikov.bullfinch.entity.UserRating;
import com.borikov.bullfinch.entity.UserRole;
import com.borikov.bullfinch.entity.Wallet;

public class UserBuilderImpl implements UserBuilder {
    private Long userId;
    private String email;
    private String login;
    private String firstName;
    private String secondName;
    private String phoneNumber;
    private boolean isBlocked;
    private boolean isActivated;
    private UserRole userRole;
    private UserRating userRating;
    private Wallet wallet;

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    @Override
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public void setUserRating(UserRating userRating) {
        this.userRating = userRating;
    }

    @Override
    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public User getUser() {
        return new User(userId, email, login, firstName, secondName, phoneNumber,
                isBlocked, isActivated, userRole, userRating, wallet);
    }
}
