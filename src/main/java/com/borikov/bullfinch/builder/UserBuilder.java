package com.borikov.bullfinch.builder;

import com.borikov.bullfinch.entity.UserRating;
import com.borikov.bullfinch.entity.UserRole;
import com.borikov.bullfinch.entity.Wallet;

public interface UserBuilder {
    void setUserId(Long userId);

    void setEmail(String email);

    void setLogin(String login);

    void setFirstName(String firstName);

    void setSecondName(String secondName);

    void setPhoneNumber(String phoneNumber);

    void setBlocked(boolean blocked);

    void setActivated(boolean activated);

    void setUserRole(UserRole userRole);

    void setUserRating(UserRating userRating);

    void setWallet(Wallet wallet);
}
