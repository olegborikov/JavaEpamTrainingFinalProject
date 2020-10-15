package com.borikov.bullfinch.service;

import com.borikov.bullfinch.entity.Order;
import com.borikov.bullfinch.exception.ServiceException;

import java.util.List;

public interface OrderService {
    boolean addOrder(String date, String description, String price,
                     String tattooId, String userLogin) throws ServiceException;

    List<Order> getOrdersByUserLogin(String userLogin) throws ServiceException;
}
