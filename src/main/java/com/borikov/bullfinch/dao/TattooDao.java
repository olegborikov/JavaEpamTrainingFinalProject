package com.borikov.bullfinch.dao;

import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface TattooDao {
    boolean add(Tattoo tattoo, Connection connection) throws DaoException;

    boolean remove(long id, Connection connection) throws DaoException;

    boolean update(Tattoo tattoo) throws DaoException;

    boolean offer(Tattoo tattoo, Connection connection) throws DaoException;

    boolean allow(long id) throws DaoException;

    boolean archive(long id) throws DaoException;

    boolean unarchive(long id) throws DaoException;

    List<Tattoo> findAll() throws DaoException;

    Optional<Tattoo> findById(long id) throws DaoException;

    List<Tattoo> findByNameSubstring(String nameSubstring) throws DaoException;

    List<Tattoo> findByAllowed(boolean isAllowed) throws DaoException;

    List<Tattoo> findByArchived(boolean isArchived) throws DaoException;

    List<Tattoo> findAllCatalog() throws DaoException;

    Optional<Tattoo> findByIdCatalog(long id) throws DaoException;

    List<Tattoo> findByNameSubstringCatalog(String nameSubstring) throws DaoException;
}
