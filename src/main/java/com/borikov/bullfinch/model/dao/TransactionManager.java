package com.borikov.bullfinch.model.dao;

import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.exception.TransactionException;
import com.borikov.bullfinch.model.dao.impl.*;
import com.borikov.bullfinch.model.dao.pool.ConnectionPool;
import com.borikov.bullfinch.model.entity.Order;
import com.borikov.bullfinch.model.entity.Tattoo;
import com.borikov.bullfinch.model.entity.User;
import com.borikov.bullfinch.model.entity.Wallet;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class TransactionManager {
    private static final Logger LOGGER = LogManager.getLogger();

    public boolean addImageAndTattoo(Tattoo tattoo) throws TransactionException {
        Connection connection = null;
        ImageDao imageDao = ImageDaoImpl.getInstance();
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            boolean result = imageDao.add(tattoo.getImage(), connection);
            if (result) {
                result = tattooDao.add(tattoo, connection);
            }
            connection.commit();
            return result;
        } catch (SQLException | DaoException e) {
            rollbackConnection(connection);
            throw new TransactionException("Error while adding image and tattoo: " + tattoo, e);
        } finally {
            closeConnection(connection);
        }
    }

    public boolean offerImageAndTattoo(Tattoo tattoo) throws TransactionException {
        Connection connection = null;
        ImageDao imageDao = ImageDaoImpl.getInstance();
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            boolean result = imageDao.add(tattoo.getImage(), connection);
            if (result) {
                result = tattooDao.offer(tattoo, connection);
            }
            connection.commit();
            return result;
        } catch (SQLException | DaoException e) {
            rollbackConnection(connection);
            throw new TransactionException("Error while offering image and tattoo: " + tattoo, e);
        } finally {
            closeConnection(connection);
        }
    }

    public boolean removeTattooAndImage(long tattooId, long imageId) throws TransactionException {
        Connection connection = null;
        ImageDao imageDao = ImageDaoImpl.getInstance();
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            boolean result = tattooDao.remove(tattooId, connection);
            if (result) {
                result = imageDao.remove(imageId, connection);
            }
            connection.commit();
            return result;
        } catch (SQLException | DaoException e) {
            rollbackConnection(connection);
            throw new TransactionException("Error while removing tattoo: id = "
                    + tattooId + " and image: id = " + imageId, e);
        } finally {
            closeConnection(connection);
        }
    }

    public boolean addWalletAndUser(User user, String password) throws TransactionException {
        Connection connection = null;
        WalletDao walletDao = WalletDaoImpl.getInstance();
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            boolean result = walletDao.add(user.getWallet(), connection);
            if (result) {
                result = userDao.add(user, password, connection);
            }
            connection.commit();
            return result;
        } catch (SQLException | DaoException e) {
            rollbackConnection(connection);
            throw new TransactionException("Error while adding wallet and user: " + user, e);
        } finally {
            closeConnection(connection);
        }
    }

    public boolean orderSubmitProcess(long orderId) throws TransactionException {
        Connection connection = null;
        boolean result = false;
        WalletDao walletDao = WalletDaoImpl.getInstance();
        OrderDao orderDao = OrderDaoImpl.getInstance();
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            Optional<Wallet> walletOptional = walletDao.findByOrderId(orderId);
            Optional<Order> orderOptional = orderDao.findById(orderId);
            if (walletOptional.isPresent() && orderOptional.isPresent()) {
                Wallet wallet = walletOptional.get();
                Order order = orderOptional.get();
                double newBalance = wallet.getBalance() - order.getPrice();
                wallet.setBalance(newBalance);
                result = walletDao.update(wallet);
                if (result) {
                    orderDao.submit(orderId);
                }
            }
            connection.commit();
            return result;
        } catch (SQLException | DaoException e) {
            rollbackConnection(connection);
            throw new TransactionException("Error while process order submit: id = " + orderId, e);
        } finally {
            closeConnection(connection);
        }
    }

    private void rollbackConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while rollback transaction: {}", connection, e);
        }
    }

    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Error while closing connection: {}", connection, e);
            }
        }
    }
}
