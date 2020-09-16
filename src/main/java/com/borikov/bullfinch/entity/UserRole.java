package com.borikov.bullfinch.entity;

public enum UserRole {// TODO: 16.09.2020 refactor
    GUEST(1, "guest"),
    USER(2, "user"),
    ADMIN(3, "admin");
    private final int userRoleId;
    private final String name;

    UserRole(int userRoleId, String name) {
        this.userRoleId = userRoleId;
        this.name = name;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public String getName() {
        return name;
    }
}
