package com.borikov.bullfinch.dao.pool;

import java.util.ResourceBundle;

public class DatabaseConfig {
    private static final String FILE_NAME = "database";
    private static final String DATABASE_DRIVER_NAME = "database.driverName";
    private static final String DATABASE_URL = "database.url";
    private static final String DATABASE_USERNAME = "database.username";
    private static final String DATABASE_PASSWORD = "database.password";
    private final String driverName;
    private final String url;
    private final String username;
    private final String password;

    DatabaseConfig() {
        ResourceBundle bundle = ResourceBundle.getBundle(FILE_NAME);
        driverName = bundle.getString(DATABASE_DRIVER_NAME);
        url = bundle.getString(DATABASE_URL);
        username = bundle.getString(DATABASE_USERNAME);
        password = bundle.getString(DATABASE_PASSWORD);
    }

    public String getDriverName() {
        return driverName;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
