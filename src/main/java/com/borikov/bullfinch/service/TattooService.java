package com.borikov.bullfinch.service;

import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface TattooService {
    List<Tattoo> findAllTattoos() throws ServiceException;

    List<Tattoo> findTattoosByName(String name) throws ServiceException;

    Optional<Tattoo> findTattooById(String tattooId) throws ServiceException;

    boolean addTattoo(String tattooName, String description, String imageName) throws ServiceException;
}
