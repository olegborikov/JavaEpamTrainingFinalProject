package com.borikov.bullfinch.model.dao;

import com.borikov.bullfinch.model.entity.Discount;
import com.borikov.bullfinch.model.exception.DaoException;

import java.util.List;

/**
 * The {@code DiscountDao} interface represents discount dao.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public interface DiscountDao {
    /**
     * Add discount.
     *
     * @param discount the discount
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(Discount discount) throws DaoException;

    /**
     * Remove discount.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean remove(long id) throws DaoException;

    /**
     * Find discounts by user login.
     *
     * @param userLogin the user login
     * @return the list of found discounts
     * @throws DaoException the dao exception
     */
    List<Discount> findByUserLogin(String userLogin) throws DaoException;
}
