package com.borikov.bullfinch.service;

import com.borikov.bullfinch.entity.Discount;
import com.borikov.bullfinch.exception.ServiceException;

import java.util.List;

public interface DiscountService {
    List<Discount> getDiscountsByUserLogin(String userLogin) throws ServiceException;

    boolean removeDiscount(String discountId) throws ServiceException;
}
