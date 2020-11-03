package com.borikov.bullfinch.model.service;

import com.borikov.bullfinch.model.entity.Discount;
import com.borikov.bullfinch.model.exception.ServiceException;

import java.util.List;

/**
 * The interface Discount service.
 */
public interface DiscountService {
    /**
     * Add discount boolean.
     *
     * @param discountPercent the discount percent
     * @param userId          the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addDiscount(String discountPercent, String userId) throws ServiceException;

    /**
     * Remove discount boolean.
     *
     * @param discountId the discount id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean removeDiscount(String discountId) throws ServiceException;

    /**
     * Find discounts by user login list.
     *
     * @param userLogin the user login
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Discount> findDiscountsByUserLogin(String userLogin) throws ServiceException;
}
