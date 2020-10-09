package com.borikov.bullfinch.dao;

import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface TattooDao {
    List<Tattoo> findAll() throws DaoException;

    List<Tattoo> findByAllowed(boolean isAllowed) throws DaoException;

    List<Tattoo> findByArchived(boolean isArchived) throws DaoException;

    List<Tattoo> findByAllowedAndArchived(// TODO: 08.10.2020 refactor name
            boolean isAllowed, boolean isArchived) throws DaoException;

    List<Tattoo> findByNameAndAllowedAndArchived(// TODO: 08.10.2020 refactor name
            String name, boolean isAllowed, boolean isArchived) throws DaoException;

    Optional<Tattoo> findByIdAndAllowedAndArchived(// TODO: 08.10.2020 refactor name
            long id, boolean isAllowed, boolean isArchived) throws DaoException;

    Optional<Tattoo> findById(long id) throws DaoException;

    boolean offer(Tattoo tattoo) throws DaoException;

    boolean allow(long id) throws DaoException;

    boolean delete(long id) throws DaoException;

    boolean archive(long id) throws DaoException;

    boolean unarchive(long id) throws DaoException;

    boolean update(Tattoo tattoo) throws DaoException;
}
