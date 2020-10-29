package com.borikov.bullfinch.model.service;

import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    boolean addOrder(String date, String description, String price, String tattooId,
                     String userLogin, String discountId) throws ServiceException;

    boolean removeOrder(String id) throws ServiceException;

    boolean submitOrder(String id) throws ServiceException;

    List<Order> findAllOrders() throws ServiceException;

    Optional<Order> findOrderById(String id) throws ServiceException;

    List<Order> findOrdersByUserLogin(String userLogin) throws ServiceException;
}
