package com.borikov.bullfinch.model.service;

import com.borikov.bullfinch.model.entity.Tattoo;
import com.borikov.bullfinch.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface Tattoo service.
 */
public interface TattooService {
    /**
     * Add tattoo boolean.
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
     * Remove tattoo boolean.
     *
     * @param tattooId the tattoo id
     * @param imageId  the image id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean removeTattoo(String tattooId, String imageId) throws ServiceException;

    /**
     * Edit tattoo boolean.
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
     * Offer tattoo boolean.
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
     * Allow tattoo boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean allowTattoo(String id) throws ServiceException;

    /**
     * Archive tattoo boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean archiveTattoo(String id) throws ServiceException;

    /**
     * Unarchive tattoo boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean unarchiveTattoo(String id) throws ServiceException;

    /**
     * Find all tattoos list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Tattoo> findAllTattoos() throws ServiceException;

    /**
     * Find tattoo by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Tattoo> findTattooById(String id) throws ServiceException;

    /**
     * Find tattoos by name substring list.
     *
     * @param nameSubstring the name substring
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Tattoo> findTattoosByNameSubstring(String nameSubstring) throws ServiceException;

    /**
     * Find tattoos by allowed list.
     *
     * @param isAllowed the is allowed
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Tattoo> findTattoosByAllowed(boolean isAllowed) throws ServiceException;

    /**
     * Find tattoos by archived list.
     *
     * @param isArchived the is archived
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Tattoo> findTattoosByArchived(boolean isArchived) throws ServiceException;

    /**
     * Find all tattoos catalog list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Tattoo> findAllTattoosCatalog() throws ServiceException;

    /**
     * Find tattoo by id catalog optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Tattoo> findTattooByIdCatalog(String id) throws ServiceException;

    /**
     * Find tattoos by name substring catalog list.
     *
     * @param nameSubstring the name substring
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Tattoo> findTattoosByNameSubstringCatalog(String nameSubstring) throws ServiceException;
}
