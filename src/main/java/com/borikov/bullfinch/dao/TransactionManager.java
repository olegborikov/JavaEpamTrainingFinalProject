package com.borikov.bullfinch.dao;

import com.borikov.bullfinch.dao.impl.*;
import com.borikov.bullfinch.dao.pool.ConnectionPool;
import com.borikov.bullfinch.entity.Order;
import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.entity.User;
import com.borikov.bullfinch.entity.Wallet;
import com.borikov.bullfinch.exception.ConnectionPoolException;
import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.exception.TransactionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class TransactionManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ImageDao imageDao = new ImageDaoImpl();
    private final TattooDao tattooDao = new TattooDaoImpl();
    private final WalletDao walletDao = new WalletDaoImpl();
    private final UserDao userDao = new UserDaoImpl();
    private final OrderDao orderDao = new OrderDaoImpl();

    public boolean addTattooTransaction(Tattoo tattoo) throws TransactionException {
        Connection connection = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            boolean result = imageDao.add(tattoo.getImage(), connection);
            if (result) {
                result = tattooDao.add(tattoo, connection);
            }
            connection.commit();
            return result;
        } catch (ConnectionPoolException | SQLException | DaoException e) {
            rollbackConnection(connection);
            throw new TransactionException("Error adding tattoo transaction", e);
        } finally {
            closeConnection(connection);
        }
    }

    public boolean offerTattooTransaction(Tattoo tattoo)
            throws TransactionException {
        Connection connection = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            boolean result = imageDao.add(tattoo.getImage(), connection);
            if (result) {
                result = tattooDao.offer(tattoo, connection);
            }
            connection.commit();
            return result;
        } catch (ConnectionPoolException | SQLException | DaoException e) {
            rollbackConnection(connection);
            throw new TransactionException("Error offering tattoo transaction", e);
        } finally {
            closeConnection(connection);
        }
    }

    public boolean removeTattooTransaction(long tattooId, long imageId)
            throws TransactionException {
        Connection connection = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            boolean result = tattooDao.remove(tattooId, connection);
            if (result) {
                result = imageDao.remove(imageId, connection);
            }
            connection.commit();
            return result;
        } catch (ConnectionPoolException | SQLException | DaoException e) {
            rollbackConnection(connection);
            throw new TransactionException("Error deleting tattoo transaction", e);
        } finally {
            closeConnection(connection);
        }
    }

    public boolean addUserTransaction(User user, String password)
            throws TransactionException {
        Connection connection = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            boolean result = walletDao.add(user.getWallet(), connection);
            if (result) {
                result = userDao.add(user, password, connection);
            }
            connection.commit();
            return result;
        } catch (ConnectionPoolException | SQLException | DaoException e) {
            rollbackConnection(connection);
            throw new TransactionException("Error adding user transaction", e);
        } finally {
            closeConnection(connection);
        }
    }

    public boolean submitOrderTransaction(long orderId) throws TransactionException {
        Connection connection = null;
        boolean result = false;
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
        } catch (ConnectionPoolException | SQLException | DaoException e) {
            rollbackConnection(connection);
            throw new TransactionException("Error adding user transaction", e);
        } finally {
            closeConnection(connection);
        }
    }

    private void rollbackConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.ERROR, "Error while rollback transaction");
        }
    }

    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Error while closing connection");
            }
        }
    }
}
