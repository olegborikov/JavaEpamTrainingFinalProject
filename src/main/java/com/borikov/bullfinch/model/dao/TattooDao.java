package com.borikov.bullfinch.model.dao;

import com.borikov.bullfinch.model.entity.Tattoo;
import com.borikov.bullfinch.model.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * The {@code TattooDao} interface represents tattoo dao.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public interface TattooDao {
    /**
     * Add tattoo.
     *
     * @param tattoo     the tattoo
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(Tattoo tattoo, Connection connection) throws DaoException;

    /**
     * Remove tattoo.
     *
     * @param id         the id
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean remove(long id, Connection connection) throws DaoException;

    /**
     * Update tattoo.
     *
     * @param tattoo the tattoo
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean update(Tattoo tattoo) throws DaoException;

    /**
     * Offer tattoo.
     *
     * @param tattoo     the tattoo
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean offer(Tattoo tattoo, Connection connection) throws DaoException;

    /**
     * Allow tattoo.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean allow(long id) throws DaoException;

    /**
     * Archive tattoo.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean archive(long id) throws DaoException;

    /**
     * Unarchive tattoo.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean unarchive(long id) throws DaoException;

    /**
     * Find all tattoos.
     *
     * @return the list of found tattoos
     * @throws DaoException the dao exception
     */
    List<Tattoo> findAll() throws DaoException;

    /**
     * Find tattoo by id.
     *
     * @param id the id
     * @return the optional of found tattoo
     * @throws DaoException the dao exception
     */
    Optional<Tattoo> findById(long id) throws DaoException;

    /**
     * Find tattoos by name substring.
     *
     * @param nameSubstring the name substring
     * @return the list of found tattoos
     * @throws DaoException the dao exception
     */
    List<Tattoo> findByNameSubstring(String nameSubstring) throws DaoException;

    /**
     * Find tattoos by allowed.
     *
     * @param isAllowed the is allowed
     * @return the list of found tattoos
     * @throws DaoException the dao exception
     */
    List<Tattoo> findByAllowed(boolean isAllowed) throws DaoException;

    /**
     * Find tattoos by archived.
     *
     * @param isArchived the is archived
     * @return the list of found tattoos
     * @throws DaoException the dao exception
     */
    List<Tattoo> findByArchived(boolean isArchived) throws DaoException;

    /**
     * Find all catalog tattoos.
     *
     * @return the list of found tattoos
     * @throws DaoException the dao exception
     */
    List<Tattoo> findAllCatalog() throws DaoException;

    /**
     * Find tattoo by id catalog.
     *
     * @param id the id
     * @return the optional of found tattoo
     * @throws DaoException the dao exception
     */
    Optional<Tattoo> findByIdCatalog(long id) throws DaoException;

    /**
     * Find tattoos by name substring catalog.
     *
     * @param nameSubstring the name substring
     * @return the list of found tattoos
     * @throws DaoException the dao exception
     */
    List<Tattoo> findByNameSubstringCatalog(String nameSubstring) throws DaoException;
}
