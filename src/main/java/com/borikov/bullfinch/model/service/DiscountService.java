package com.borikov.bullfinch.model.service;

import com.borikov.bullfinch.model.entity.Discount;
import com.borikov.bullfinch.model.exception.ServiceException;

import java.util.List;

/**
 * The {@code DiscountService} interface represents discount service.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public interface DiscountService {
    /**
     * Add discount.
     *
     * @param percent the percent
     * @param userId  the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addDiscount(String percent, String userId) throws ServiceException;

    /**
     * Remove discount.
     *
     * @param discountId the discount id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean removeDiscount(String discountId) throws ServiceException;

    /**
     * Find discounts by user login.
     *
     * @param userLogin the user login
     * @return the list of found discounts
     * @throws ServiceException the service exception
     */
    List<Discount> findDiscountsByUserLogin(String userLogin) throws ServiceException;
}
