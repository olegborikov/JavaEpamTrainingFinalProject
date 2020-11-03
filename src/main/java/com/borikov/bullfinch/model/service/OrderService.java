package com.borikov.bullfinch.model.service;

import com.borikov.bullfinch.model.entity.Order;
import com.borikov.bullfinch.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface Order service.
 */
public interface OrderService {
    /**
     * Add order boolean.
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
     * Remove order boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean removeOrder(String id) throws ServiceException;

    /**
     * Submit order boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean submitOrder(String id) throws ServiceException;

    /**
     * Find all orders list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findAllOrders() throws ServiceException;

    /**
     * Find order by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Order> findOrderById(String id) throws ServiceException;

    /**
     * Find orders by dates list.
     *
     * @param beginDate the begin date
     * @param endDate   the end date
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findOrdersByDates(String beginDate, String endDate) throws ServiceException;

    /**
     * Find orders by user login list.
     *
     * @param userLogin the user login
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findOrdersByUserLogin(String userLogin) throws ServiceException;
}
