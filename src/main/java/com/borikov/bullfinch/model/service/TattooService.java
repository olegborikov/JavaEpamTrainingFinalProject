package com.borikov.bullfinch.model.service;

import com.borikov.bullfinch.model.entity.Tattoo;
import com.borikov.bullfinch.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The {@code TattooService} interface represents tattoo service.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public interface TattooService {
    /**
     * Add tattoo.
     *
     * @param tattooName    the tattoo name
     * @param description   the description
     * @param price         the price
     * @param imageName     the image name
     * @param proposedLogin the proposed login
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addTattoo(String tattooName, String description, String price, String imageName,
                      String proposedLogin) throws ServiceException;

    /**
     * Remove tattoo.
     *
     * @param tattooId the tattoo id
     * @param imageId  the image id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean removeTattoo(String tattooId, String imageId) throws ServiceException;

    /**
     * Edit tattoo.
     *
     * @param id          the id
     * @param name        the name
     * @param description the description
     * @param price       the price
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean editTattoo(String id, String name, String description, String price) throws ServiceException;

    /**
     * Offer tattoo.
     *
     * @param tattooName    the tattoo name
     * @param description   the description
     * @param price         the price
     * @param imageName     the image name
     * @param proposedLogin the proposed login
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean offerTattoo(String tattooName, String description, String price, String imageName,
                        String proposedLogin) throws ServiceException;

    /**
     * Allow tattoo.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean allowTattoo(String id) throws ServiceException;

    /**
     * Archive tattoo.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean archiveTattoo(String id) throws ServiceException;

    /**
     * Unarchive tattoo.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean unarchiveTattoo(String id) throws ServiceException;

    /**
     * Find all tattoos.
     *
     * @return the list of found tattoos
     * @throws ServiceException the service exception
     */
    List<Tattoo> findAllTattoos() throws ServiceException;

    /**
     * Find tattoo by id.
     *
     * @param id the id
     * @return the optional of found tattoo
     * @throws ServiceException the service exception
     */
    Optional<Tattoo> findTattooById(String id) throws ServiceException;

    /**
     * Find tattoos by name substring.
     *
     * @param nameSubstring the name substring
     * @return the list of found tattoos
     * @throws ServiceException the service exception
     */
    List<Tattoo> findTattoosByNameSubstring(String nameSubstring) throws ServiceException;

    /**
     * Find tattoos by allowed.
     *
     * @param isAllowed the is allowed
     * @return the list of found tattoos
     * @throws ServiceException the service exception
     */
    List<Tattoo> findTattoosByAllowed(boolean isAllowed) throws ServiceException;

    /**
     * Find tattoos by archived.
     *
     * @param isArchived the is archived
     * @return the list of found tattoos
     * @throws ServiceException the service exception
     */
    List<Tattoo> findTattoosByArchived(boolean isArchived) throws ServiceException;

    /**
     * Find all tattoos catalog.
     *
     * @return the list of found tattoos
     * @throws ServiceException the service exception
     */
    List<Tattoo> findAllTattoosCatalog() throws ServiceException;

    /**
     * Find tattoo by id catalog.
     *
     * @param id the id
     * @return the optional of found tattoo
     * @throws ServiceException the service exception
     */
    Optional<Tattoo> findTattooByIdCatalog(String id) throws ServiceException;

    /**
     * Find tattoos by name substring catalog.
     *
     * @param nameSubstring the name substring
     * @return the list of found tattoos
     * @throws ServiceException the service exception
     */
    List<Tattoo> findTattoosByNameSubstringCatalog(String nameSubstring) throws ServiceException;
}
