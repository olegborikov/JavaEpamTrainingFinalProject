package com.borikov.bullfinch.dao.pool;

import com.borikov.bullfinch.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public enum ConnectionPool {
    INSTANCE;

    private final Logger logger = LogManager.getLogger();
    private final BlockingDeque<ProxyConnection> freeConnections;
    private final Queue<ProxyConnection> givenConnections;
    private static final int POOL_SIZE = 8;

    ConnectionPool() {
        try {
            DatabaseConfig databaseConfig = new DatabaseConfig();
            String driverName = databaseConfig.getDriverName();
            String url = databaseConfig.getUrl();
            String username = databaseConfig.getUsername();
            String password = databaseConfig.getPassword();
            Class.forName(driverName);
            freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
            givenConnections = new ArrayDeque<>();
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection =
                        DriverManager.getConnection(url, username, password);
                freeConnections.offer(new ProxyConnection(connection));
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.log(Level.FATAL, "Error with database", e);
            throw new RuntimeException("Error with database", e);
        }
    }

    public Connection getConnection() throws ConnectionPoolException {
        try {
            ProxyConnection connection = freeConnections.take();
            givenConnections.offer(connection);
            return connection;
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Throw was interrupted", e);
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection
                && givenConnections.remove(connection)) {
            freeConnections.offer((ProxyConnection) connection);
        } else {
            logger.log(Level.ERROR, "Connection {} is invalid", connection);
        }
    }

    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Connection was not deleted", e);
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "Throw was interrupted", e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Driver was not deregister", e);
            }
        });
    }
}
