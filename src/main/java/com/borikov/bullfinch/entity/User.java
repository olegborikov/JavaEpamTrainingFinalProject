package com.borikov.bullfinch.entity;

public class User {
    private Long userId;
    private String email;
    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private String phoneNumber;
    private boolean isBlocked;
    private boolean isActivated;
    private UserRole userRole;
    private UserRating userRating;
    private Wallet wallet;

    public User(Long userId, String login, String password,
                boolean isActivated, UserRole userRole) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.isActivated = isActivated;
        this.userRole = userRole;
    }

    public User(Long userId, String email, String login,
                String password, String firstName, String secondName,
                String phoneNumber, boolean isBlocked,
                boolean isActivated, UserRole userRole,
                UserRating userRating, Wallet wallet) {
        this.userId = userId;
        this.email = email;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.isBlocked = isBlocked;
        this.isActivated = isActivated;
        this.userRole = userRole;
        this.userRating = userRating;
        this.wallet = wallet;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserRating getUserRating() {
        return userRating;
    }

    public void setUserRating(UserRating userRating) {
        this.userRating = userRating;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        if (isBlocked != user.isBlocked) {
            return false;
        }
        if (isActivated != user.isActivated) {
            return false;
        }
        if (userId != null ? !userId.equals(user.userId) : user.userId != null) {
            return false;
        }
        if (email != null ? !email.equals(user.email) : user.email != null) {
            return false;
        }
        if (login != null ? !login.equals(user.login) : user.login != null) {
            return false;
        }
        if (password != null ? !password.equals(user.password)
                : user.password != null) {
            return false;
        }
        if (firstName != null ? !firstName.equals(user.firstName)
                : user.firstName != null) {
            return false;
        }
        if (secondName != null ? !secondName.equals(user.secondName)
                : user.secondName != null) {
            return false;
        }
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber)
                : user.phoneNumber != null) {
            return false;
        }
        if (userRole != user.userRole) {
            return false;
        }
        if (userRating != user.userRating) {
            return false;
        }
        return wallet != null ? wallet.equals(user.wallet) : user.wallet == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (isBlocked ? 1 : 0);
        result = 31 * result + (isActivated ? 1 : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        result = 31 * result + (userRating != null ? userRating.hashCode() : 0);
        result = 31 * result + (wallet != null ? wallet.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(userId);
        sb.append(", email='").append(email).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", secondName='").append(secondName).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", isBlocked=").append(isBlocked);
        sb.append(", isActivated=").append(isActivated);
        sb.append(", userRole=").append(userRole);
        sb.append(", userRating=").append(userRating);
        sb.append(", wallet=").append(wallet);
        sb.append('}');
        return sb.toString();
    }
}
