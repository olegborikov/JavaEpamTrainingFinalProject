package com.borikov.bullfinch.service.impl;

import com.borikov.bullfinch.dao.TattooDao;
import com.borikov.bullfinch.dao.impl.TattooDaoImpl;
import com.borikov.bullfinch.entity.Image;
import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.service.TattooService;
import com.borikov.bullfinch.validator.TattooValidator;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Tattoo> findTattooById(String tattooId) throws ServiceException {
        try {
            long id = Long.parseLong(tattooId);
            Optional<Tattoo> tattoo = tattooDao.findById(id);
            return tattoo;
        } catch (DaoException e) {
            throw new ServiceException("Error while finding tattoos by name", e);
        }
    }

    @Override
    public boolean addTattoo(String tattooName, String description, String imageName)
            throws ServiceException {
        try {
            TattooValidator tattooValidator = new TattooValidator();
            boolean result = false;
            if (tattooValidator.isNameCorrect(tattooName)
                    && tattooValidator.isDescriptionCorrect(description)) {
                Tattoo tattoo = new Tattoo(tattooName, description, new Image(null, imageName));
                result = tattooDao.add(tattoo);
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Error while checking user for existing", e);
        }
    }
}
