package com.borikov.bullfinch.service;

import com.borikov.bullfinch.entity.Order;
import com.borikov.bullfinch.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    boolean addOrder(String date, String description, String price,
                     String tattooId, String userLogin) throws ServiceException;

    List<Order> getOrdersByUserLogin(String userLogin) throws ServiceException;

    Optional<Order> findOrderById(String id) throws ServiceException;

    boolean removeOrder(String id) throws ServiceException;
}
