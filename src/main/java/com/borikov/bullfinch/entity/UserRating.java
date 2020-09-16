package com.borikov.bullfinch.entity;

public enum UserRating {// TODO: 16.09.2020 refactor
    BEGINNER(1, "beginner"),
    MIDDLE(2, "middle"),
    ADVANCED(3, "advanced"),
    MASTER(4, "master");

    private final int userRatingId;
    private final String name;

    UserRating(int userRatingId, String name) {
        this.userRatingId = userRatingId;
        this.name = name;
    }

    public int getUserRatingId() {
        return userRatingId;
    }

    public String getName() {
        return name;
    }
}
