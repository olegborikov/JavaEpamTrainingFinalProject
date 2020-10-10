package com.borikov.bullfinch.service;

import com.borikov.bullfinch.exception.ServiceException;

public interface OrderService {
    boolean addOrder(String date, String description, String price,
                     String tattooId, String userLogin) throws ServiceException;
}
