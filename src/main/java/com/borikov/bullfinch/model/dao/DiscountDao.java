package com.borikov.bullfinch.model.dao;

import com.borikov.bullfinch.model.entity.Discount;
import com.borikov.bullfinch.model.exception.DaoException;

import java.util.List;

/**
 * The interface Discount dao.
 */
public interface DiscountDao {
    /**
     * Add boolean.
     *
     * @param discount the discount
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(Discount discount) throws DaoException;

    /**
     * Remove boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean remove(long id) throws DaoException;

    /**
     * Find by user login list.
     *
     * @param userLogin the user login
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Discount> findByUserLogin(String userLogin) throws DaoException;
}
