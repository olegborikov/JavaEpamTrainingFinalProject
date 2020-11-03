package com.borikov.bullfinch.model.dao;

import com.borikov.bullfinch.model.entity.Tattoo;
import com.borikov.bullfinch.model.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * The interface Tattoo dao.
 */
public interface TattooDao {
    /**
     * Add boolean.
     *
     * @param tattoo     the tattoo
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(Tattoo tattoo, Connection connection) throws DaoException;

    /**
     * Remove boolean.
     *
     * @param id         the id
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean remove(long id, Connection connection) throws DaoException;

    /**
     * Update boolean.
     *
     * @param tattoo the tattoo
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean update(Tattoo tattoo) throws DaoException;

    /**
     * Offer boolean.
     *
     * @param tattoo     the tattoo
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean offer(Tattoo tattoo, Connection connection) throws DaoException;

    /**
     * Allow boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean allow(long id) throws DaoException;

    /**
     * Archive boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean archive(long id) throws DaoException;

    /**
     * Unarchive boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean unarchive(long id) throws DaoException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Tattoo> findAll() throws DaoException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Tattoo> findById(long id) throws DaoException;

    /**
     * Find by name substring list.
     *
     * @param nameSubstring the name substring
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Tattoo> findByNameSubstring(String nameSubstring) throws DaoException;

    /**
     * Find by allowed list.
     *
     * @param isAllowed the is allowed
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Tattoo> findByAllowed(boolean isAllowed) throws DaoException;

    /**
     * Find by archived list.
     *
     * @param isArchived the is archived
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Tattoo> findByArchived(boolean isArchived) throws DaoException;

    /**
     * Find all catalog list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Tattoo> findAllCatalog() throws DaoException;

    /**
     * Find by id catalog optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Tattoo> findByIdCatalog(long id) throws DaoException;

    /**
     * Find by name substring catalog list.
     *
     * @param nameSubstring the name substring
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Tattoo> findByNameSubstringCatalog(String nameSubstring) throws DaoException;
}
