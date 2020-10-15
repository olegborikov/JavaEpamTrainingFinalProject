package com.borikov.bullfinch.entity;

/**
 * The {@code UserRole} class represents role entity
 *
 * @author Oleg Borikov
 * @version 1.0
 * @since 2020-10-16
 */
public enum UserRole {
    /**
     * Guest user role.
     */
    GUEST(1, "guest"),
    /**
     * User user role.
     */
    USER(2, "user"),
    /**
     * Admin user role.
     */
    ADMIN(3, "admin");

    /**
     * The value is used for user role id storage.
     */
    private final int userRoleId;

    /**
     * The value is used for name storage.
     */
    private final String name;

    /**
     * @param userRoleId the user role id
     * @param name       the name
     */
    UserRole(int userRoleId, String name) {
        this.userRoleId = userRoleId;
        this.name = name;
    }

    /**
     * Gets user role id.
     *
     * @return the user role id
     */
    public int getUserRoleId() {
        return userRoleId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
