package com.borikov.bullfinch.model.dao;

import com.borikov.bullfinch.model.entity.Order;
import com.borikov.bullfinch.model.exception.DaoException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The interface Order dao.
 */
public interface OrderDao {
    /**
     * Add boolean.
     *
     * @param order the order
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(Order order) throws DaoException;

    /**
     * Remove boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean remove(long id) throws DaoException;

    /**
     * Submit boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean submit(long id) throws DaoException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findAll() throws DaoException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Order> findById(long id) throws DaoException;

    /**
     * Find by dates list.
     *
     * @param beginDate the begin date
     * @param endDate   the end date
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findByDates(LocalDate beginDate, LocalDate endDate) throws DaoException;

    /**
     * Find by user login list.
     *
     * @param userLogin the user login
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findByUserLogin(String userLogin) throws DaoException;
}
