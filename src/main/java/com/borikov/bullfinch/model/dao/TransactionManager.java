package com.borikov.bullfinch.model.dao;

import com.borikov.bullfinch.model.dao.impl.*;
import com.borikov.bullfinch.model.entity.Order;
import com.borikov.bullfinch.model.entity.Tattoo;
import com.borikov.bullfinch.model.entity.User;
import com.borikov.bullfinch.model.entity.Wallet;
import com.borikov.bullfinch.model.exception.DaoException;
import com.borikov.bullfinch.model.exception.TransactionException;
import com.borikov.bullfinch.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

/**
 * The {@code TransactionManager} class represents transaction manager.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class TransactionManager {
    private static final TransactionManager INSTANCE = new TransactionManager();
    private static final Logger LOGGER = LogManager.getLogger();

    private TransactionManager() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static TransactionManager getInstance() {
        return INSTANCE;
    }

    /**
     * Add image and tattoo transaction.
     *
     * @param tattoo the tattoo
     * @return the boolean
     * @throws TransactionException the transaction exception
     */
    public boolean addImageAndTattoo(Tattoo tattoo) throws TransactionException {
        Connection connection = null;
        ImageDao imageDao = ImageDaoImpl.getInstance();
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            boolean isImageAndTattooAdded = imageDao.add(tattoo.getImage(), connection);
            if (isImageAndTattooAdded) {
                isImageAndTattooAdded = tattooDao.add(tattoo, connection);
            }
            connection.commit();
            return isImageAndTattooAdded;
        } catch (SQLException | DaoException e) {
            rollbackConnection(connection);
            throw new TransactionException("Error while adding image and tattoo: " + tattoo, e);
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * Offer image and tattoo transaction.
     *
     * @param tattoo the tattoo
     * @return the boolean
     * @throws TransactionException the transaction exception
     */
    public boolean offerImageAndTattoo(Tattoo tattoo) throws TransactionException {
        Connection connection = null;
        ImageDao imageDao = ImageDaoImpl.getInstance();
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            boolean isImageAndTattooOffered = imageDao.add(tattoo.getImage(), connection);
            if (isImageAndTattooOffered) {
                isImageAndTattooOffered = tattooDao.offer(tattoo, connection);
            }
            connection.commit();
            return isImageAndTattooOffered;
        } catch (SQLException | DaoException e) {
            rollbackConnection(connection);
            throw new TransactionException("Error while offering image and tattoo: " + tattoo, e);
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * Remove tattoo and image transaction.
     *
     * @param tattooId the tattoo id
     * @param imageId  the image id
     * @return the boolean
     * @throws TransactionException the transaction exception
     */
    public boolean removeTattooAndImage(long tattooId, long imageId) throws TransactionException {
        Connection connection = null;
        ImageDao imageDao = ImageDaoImpl.getInstance();
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            boolean isTattooAndImageRemoved = tattooDao.remove(tattooId, connection);
            if (isTattooAndImageRemoved) {
                isTattooAndImageRemoved = imageDao.remove(imageId, connection);
            }
            connection.commit();
            return isTattooAndImageRemoved;
        } catch (SQLException | DaoException e) {
            rollbackConnection(connection);
            StringBuilder sb = new StringBuilder("Error while removing tattoo: ");
            sb.append("id = ").append(tattooId);
            sb.append(" and image: ");
            sb.append("id = ").append(imageId);
            throw new TransactionException(sb.toString(), e);
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * Add wallet and user transaction.
     *
     * @param user     the user
     * @param password the password
     * @return the boolean
     * @throws TransactionException the transaction exception
     */
    public boolean addWalletAndUser(User user, String password) throws TransactionException {
        Connection connection = null;
        WalletDao walletDao = WalletDaoImpl.getInstance();
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            boolean isWalletAndUserAdded = walletDao.add(user.getWallet(), connection);
            if (isWalletAndUserAdded) {
                isWalletAndUserAdded = userDao.add(user, password, connection);
            }
            connection.commit();
            return isWalletAndUserAdded;
        } catch (SQLException | DaoException e) {
            rollbackConnection(connection);
            throw new TransactionException("Error while adding wallet and user: " + user, e);
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * Order submit process transaction.
     *
     * @param orderId the order id
     * @return the boolean
     * @throws TransactionException the transaction exception
     */
    public boolean orderSubmitProcess(long orderId) throws TransactionException {
        Connection connection = null;
        boolean isOrderSubmitted = false;
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
                isOrderSubmitted = walletDao.update(wallet);
                if (isOrderSubmitted) {
                    orderDao.submit(orderId);
                }
            }
            connection.commit();
            return isOrderSubmitted;
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
