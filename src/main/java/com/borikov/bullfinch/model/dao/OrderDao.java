package com.borikov.bullfinch.model.dao;

import com.borikov.bullfinch.model.entity.Order;
import com.borikov.bullfinch.model.exception.DaoException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The {@code OrderDao} interface represents order dao.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public interface OrderDao {
    /**
     * Add order.
     *
     * @param order the order
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(Order order) throws DaoException;

    /**
     * Remove order.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean remove(long id) throws DaoException;

    /**
     * Submit order.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean submit(long id) throws DaoException;

    /**
     * Find all orders.
     *
     * @return the list of found orders
     * @throws DaoException the dao exception
     */
    List<Order> findAll() throws DaoException;

    /**
     * Find order by id.
     *
     * @param id the id
     * @return the optional of found order
     * @throws DaoException the dao exception
     */
    Optional<Order> findById(long id) throws DaoException;

    /**
     * Find orders by dates.
     *
     * @param beginDate the begin date
     * @param endDate   the end date
     * @return the list of found orders
     * @throws DaoException the dao exception
     */
    List<Order> findByDates(LocalDate beginDate, LocalDate endDate) throws DaoException;

    /**
     * Find orders by user login.
     *
     * @param userLogin the user login
     * @return the list of found orders
     * @throws DaoException the dao exception
     */
    List<Order> findByUserLogin(String userLogin) throws DaoException;
}
