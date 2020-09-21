package com.borikov.bullfinch.service.impl;

import com.borikov.bullfinch.dao.TattooDao;
import com.borikov.bullfinch.dao.impl.TattooDaoImpl;
import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.service.TattooService;

import java.util.List;

public class TattooServiceImpl implements TattooService {
    private final TattooDao tattooDao = new TattooDaoImpl();

    @Override
    public List<Tattoo> findAllTattoos() throws ServiceException {
        try {
            List<Tattoo> tattoos = tattooDao.findAll();
            return tattoos;
        } catch (DaoException e) {
            throw new ServiceException("Error while finding tattoos", e);
        }
    }

    @Override
    public List<Tattoo> findTattoosByName(String name) throws ServiceException {
        try {
            List<Tattoo> tattoos = tattooDao.findByName(name);
            return tattoos;
        } catch (DaoException e) {
            throw new ServiceException("Error while finding tattoos by name", e);
        }
    }
}