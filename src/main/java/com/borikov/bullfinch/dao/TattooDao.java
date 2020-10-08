package com.borikov.bullfinch.dao;

import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface TattooDao {
    List<Tattoo> findAll() throws DaoException;

    List<Tattoo> findByAllowed(boolean isAllowed) throws DaoException;

    List<Tattoo> findByArchived(boolean isArchived) throws DaoException;

    List<Tattoo> findByAllowedAndArchived(
            boolean isAllowed, boolean isArchived) throws DaoException;

    List<Tattoo> findByNameAndAllowedAndArchived(
            String name, boolean isAllowed, boolean isArchived) throws DaoException;

    Optional<Tattoo> findByIdAndAllowedAndArchived(
            long id, boolean isAllowed, boolean isArchived) throws DaoException;

    Optional<Tattoo> findById(long id) throws DaoException;

    boolean offer(Tattoo tattoo) throws DaoException;
}
