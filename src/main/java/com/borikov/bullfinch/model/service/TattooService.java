package com.borikov.bullfinch.model.service;

import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.model.entity.Tattoo;

import java.util.List;
import java.util.Optional;

public interface TattooService {
    boolean addTattoo(String tattooName, String description, String price, String imageName,
                      String proposedLogin) throws ServiceException;

    boolean removeTattoo(String tattooId, String imageId) throws ServiceException;

    boolean editTattoo(String id, String name, String description, String price) throws ServiceException;

    boolean offerTattoo(String tattooName, String description, String price, String imageName,
                        String proposedLogin) throws ServiceException;

    boolean allowTattoo(String id) throws ServiceException;

    boolean archiveTattoo(String id) throws ServiceException;

    boolean unarchiveTattoo(String id) throws ServiceException;

    List<Tattoo> findAllTattoos() throws ServiceException;

    Optional<Tattoo> findTattooById(String id) throws ServiceException;

    List<Tattoo> findTattoosByNameSubstring(String nameSubstring) throws ServiceException;

    List<Tattoo> findTattoosByAllowed(boolean isAllowed) throws ServiceException;

    List<Tattoo> findTattoosByArchived(boolean isArchived) throws ServiceException;

    List<Tattoo> findAllTattoosCatalog() throws ServiceException;

    Optional<Tattoo> findTattooByIdCatalog(String id) throws ServiceException;

    List<Tattoo> findTattoosByNameSubstringCatalog(String nameSubstring) throws ServiceException;
}
