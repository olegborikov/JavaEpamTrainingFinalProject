package com.borikov.bullfinch.model.service;

import com.borikov.bullfinch.model.entity.Order;
import com.borikov.bullfinch.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The {@code OrderService} interface represents order service.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public interface OrderService {
    /**
     * Add order.
     *
     * @param date        the date
     * @param description the description
     * @param price       the price
     * @param tattooId    the tattoo id
     * @param userLogin   the user login
     * @param discountId  the discount id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addOrder(String date, String description, String price, String tattooId,
                     String userLogin, String discountId) throws ServiceException;

    /**
     * Remove order.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean removeOrder(String id) throws ServiceException;

    /**
     * Submit order.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean submitOrder(String id) throws ServiceException;

    /**
     * Find all orders.
     *
     * @return the list of found orders
     * @throws ServiceException the service exception
     */
    List<Order> findAllOrders() throws ServiceException;

    /**
     * Find order by id.
     *
     * @param id the id
     * @return the optional of found order
     * @throws ServiceException the service exception
     */
    Optional<Order> findOrderById(String id) throws ServiceException;

    /**
     * Find orders by dates.
     *
     * @param beginDate the begin date
     * @param endDate   the end date
     * @return the list of found orders
     * @throws ServiceException the service exception
     */
    List<Order> findOrdersByDates(String beginDate, String endDate) throws ServiceException;

    /**
     * Find orders by user login.
     *
     * @param userLogin the user login
     * @return the list of found orders
     * @throws ServiceException the service exception
     */
    List<Order> findOrdersByUserLogin(String userLogin) throws ServiceException;
}
