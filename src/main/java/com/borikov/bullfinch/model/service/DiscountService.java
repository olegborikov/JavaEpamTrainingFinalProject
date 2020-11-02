package com.borikov.bullfinch.model.service;

import com.borikov.bullfinch.model.entity.Discount;
import com.borikov.bullfinch.model.exception.ServiceException;

import java.util.List;

public interface DiscountService {
    boolean addDiscount(String discountPercent, String userId) throws ServiceException;

    boolean removeDiscount(String discountId) throws ServiceException;

    List<Discount> findDiscountsByUserLogin(String userLogin) throws ServiceException;
}
