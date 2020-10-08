package com.borikov.bullfinch.service;

import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface TattooService {
    List<Tattoo> findAllTattoos() throws ServiceException;

    List<Tattoo> findTattoosByAllowed(boolean isAllowed) throws ServiceException;

    List<Tattoo> findTattoosByArchived(boolean isArchived) throws ServiceException;

    List<Tattoo> findTattoosByAllowedAndArchived(
            boolean isAllowed, boolean isArchived) throws ServiceException;

    List<Tattoo> findTattoosByNameAndAllowedAndArchived(
            String name, boolean isAllowed, boolean isArchived) throws ServiceException;

    Optional<Tattoo> findTattooByIdAndAllowedAndArchived(
            String id, boolean isAllowed, boolean isArchived) throws ServiceException;

    Optional<Tattoo> findTattooById(String id) throws ServiceException;

    boolean offerTattoo(String tattooName, String description, String imageName) throws ServiceException;

    boolean allowTattoo(String id) throws ServiceException;

    boolean deleteTattoo(String id) throws ServiceException;

    boolean archiveTattoo(String id) throws ServiceException;

    boolean unarchiveTattoo(String id) throws ServiceException;
}
