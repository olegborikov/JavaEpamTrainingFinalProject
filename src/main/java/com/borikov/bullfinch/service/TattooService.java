package com.borikov.bullfinch.service;

import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.exception.ServiceException;

import java.util.List;

public interface TattooService {
    List<Tattoo> findAllTattoos() throws ServiceException;

    List<Tattoo> findTattoosByName(String name) throws ServiceException;
}
