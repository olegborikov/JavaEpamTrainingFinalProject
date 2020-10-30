package com.borikov.bullfinch.model.dao;

import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.model.entity.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderDao {
    boolean add(Order order) throws DaoException;

    boolean remove(long id) throws DaoException;

    boolean submit(long id) throws DaoException;

    List<Order> findAll() throws DaoException;

    Optional<Order> findById(long id) throws DaoException;

    List<Order> findByDates(LocalDate beginDate, LocalDate endDate) throws DaoException;

    List<Order> findByUserLogin(String userLogin) throws DaoException;
}
