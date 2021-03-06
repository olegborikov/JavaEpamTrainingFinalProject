package com.borikov.bullfinch.model.entity;

/**
 * The {@code User} class represents user entity.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class User {
    /**
     * The value is used for user id storage.
     */
    private Long userId;

    /**
     * The value is used for email storage.
     */
    private String email;

    /**
     * The value is used for login storage.
     */
    private String login;

    /**
     * The value is used for first name storage.
     */
    private String firstName;

    /**
     * The value is used for second name storage.
     */
    private String secondName;

    /**
     * The value is used for phone number storage.
     */
    private String phoneNumber;

    /**
     * The value is used for blocked storage.
     */
    private boolean isBlocked;

    /**
     * The value is used for activated storage.
     */
    private boolean isActivated;

    /**
     * The value is used for user role storage.
     */
    private UserRole userRole;

    /**
     * The value is used for wallet storage.
     */
    private Wallet wallet;

    /**
     * Instantiates a new User.
     *
     * @param userId      the user id
     * @param email       the email
     * @param login       the login
     * @param firstName   the first name
     * @param secondName  the second name
     * @param phoneNumber the phone number
     * @param isBlocked   the is blocked
     * @param isActivated the is activated
     * @param userRole    the user role
     * @param wallet      the wallet
     */
    public User(Long userId, String email, String login, String firstName, String secondName,
                String phoneNumber, boolean isBlocked, boolean isActivated, UserRole userRole, Wallet wallet) {
        this.userId = userId;
        this.email = email;
        this.login = login;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.isBlocked = isBlocked;
        this.isActivated = isActivated;
        this.userRole = userRole;
        this.wallet = wallet;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
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
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
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
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
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
     * Gets second name.
     *
     * @return the second name
     */
    public String getSecondName() {
        return secondName;
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
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
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
     * Gets blocked.
     *
     * @return the boolean
     */
    public boolean isBlocked() {
        return isBlocked;
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
     * Gets activated.
     *
     * @return the boolean
     */
    public boolean isActivated() {
        return isActivated;
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
     * Gets user role.
     *
     * @return the user role
     */
    public UserRole getUserRole() {
        return userRole;
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
     * Gets wallet.
     *
     * @return the wallet
     */
    public Wallet getWallet() {
        return wallet;
    }

    /**
     * Sets wallet.
     *
     * @param wallet the wallet
     */
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
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) {
            return false;
        }
        if (secondName != null ? !secondName.equals(user.secondName) : user.secondName != null) {
            return false;
        }
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) {
            return false;
        }
        if (userRole != user.userRole) {
            return false;
        }
        return wallet != null ? wallet.equals(user.wallet) : user.wallet == null;
    }

    @Override
    public int hashCode() {
        int hashCode = userId != null ? userId.hashCode() : 0;
        hashCode = 31 * hashCode + (email != null ? email.hashCode() : 0);
        hashCode = 31 * hashCode + (login != null ? login.hashCode() : 0);
        hashCode = 31 * hashCode + (firstName != null ? firstName.hashCode() : 0);
        hashCode = 31 * hashCode + (secondName != null ? secondName.hashCode() : 0);
        hashCode = 31 * hashCode + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        hashCode = 31 * hashCode + (isBlocked ? 1 : 0);
        hashCode = 31 * hashCode + (isActivated ? 1 : 0);
        hashCode = 31 * hashCode + (userRole != null ? userRole.hashCode() : 0);
        hashCode = 31 * hashCode + (wallet != null ? wallet.hashCode() : 0);
        return hashCode;
    }

    @Override
    public String toString() {
        final StringBuilder stringRepresentation = new StringBuilder("User{");
        stringRepresentation.append("userId=").append(userId);
        stringRepresentation.append(", email='").append(email).append('\'');
        stringRepresentation.append(", login='").append(login).append('\'');
        stringRepresentation.append(", firstName='").append(firstName).append('\'');
        stringRepresentation.append(", secondName='").append(secondName).append('\'');
        stringRepresentation.append(", phoneNumber='").append(phoneNumber).append('\'');
        stringRepresentation.append(", isBlocked=").append(isBlocked);
        stringRepresentation.append(", isActivated=").append(isActivated);
        stringRepresentation.append(", userRole=").append(userRole);
        stringRepresentation.append(", wallet=").append(wallet);
        stringRepresentation.append('}');
        return stringRepresentation.toString();
    }
}
