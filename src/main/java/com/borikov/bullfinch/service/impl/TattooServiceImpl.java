package com.borikov.bullfinch.service.impl;

import com.borikov.bullfinch.dao.TattooDao;
import com.borikov.bullfinch.dao.impl.TattooDaoImpl;
import com.borikov.bullfinch.entity.Image;
import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.service.TattooService;
import com.borikov.bullfinch.validator.TattooValidator;

import java.util.ArrayList;
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
    public List<Tattoo> findTattoosByAllowed(boolean isAllowed) throws ServiceException {
        try {
            List<Tattoo> tattoos = tattooDao.findByAllowed(isAllowed);
            return tattoos;
        } catch (DaoException e) {
            throw new ServiceException("Error while finding tattoos", e);
        }
    }

    @Override
    public List<Tattoo> findTattoosByArchived(boolean isArchived) throws ServiceException {
        try {
            List<Tattoo> tattoos = tattooDao.findByArchived(isArchived);
            return tattoos;
        } catch (DaoException e) {
            throw new ServiceException("Error while finding tattoos", e);
        }
    }

    @Override
    public List<Tattoo> findTattoosByAllowedAndArchived(
            boolean isAllowed, boolean isArchived) throws ServiceException {
        try {
            List<Tattoo> tattoos = tattooDao.findByAllowedAndArchived(isAllowed, isArchived);
            return tattoos;
        } catch (DaoException e) {
            throw new ServiceException("Error while finding tattoos", e);
        }
    }

    @Override
    public List<Tattoo> findTattoosByNameAndAllowedAndArchived(
            String name, boolean isAllowed, boolean isArchived) throws ServiceException {
        List<Tattoo> tattoos = new ArrayList<>();
        TattooValidator tattooValidator = new TattooValidator();
        try {
            if (tattooValidator.isNameCorrect(name)) {
                tattoos = tattooDao.findByNameAndAllowedAndArchived(name, isAllowed, isArchived);
            }
            return tattoos;
        } catch (DaoException e) {
            throw new ServiceException("Error while finding tattoos by name", e);
        }
    }

    @Override
    public Optional<Tattoo> findTattooByIdAndAllowedAndArchived(
            String id, boolean isAllowed, boolean isArchived) throws ServiceException {
        Optional<Tattoo> tattoo = Optional.empty();
        TattooValidator tattooValidator = new TattooValidator();
        try {
            if (tattooValidator.isIdCorrect(id)) {
                long tattooId = Long.parseLong(id);
                tattoo = tattooDao.findByIdAndAllowedAndArchived(tattooId, isAllowed, isArchived);
            }
            return tattoo;
        } catch (DaoException e) {
            throw new ServiceException("Error while finding tattoos by name", e);
        }
    }

    @Override
    public Optional<Tattoo> findTattooById(String id) throws ServiceException {
        Optional<Tattoo> tattoo = Optional.empty();
        TattooValidator tattooValidator = new TattooValidator();
        try {
            if (tattooValidator.isIdCorrect(id)) {
                long tattooId = Long.parseLong(id);
                tattoo = tattooDao.findById(tattooId);
            }
            return tattoo;
        } catch (DaoException e) {
            throw new ServiceException("Error while finding tattoos by name", e);
        }
    }

    @Override
    public boolean offerTattoo(String tattooName, String description, String imageName)
            throws ServiceException {
        try {
            TattooValidator tattooValidator = new TattooValidator();
            boolean result = false;
            if (tattooValidator.isNameCorrect(tattooName)
                    && tattooValidator.isDescriptionCorrect(description)) {
                Tattoo tattoo = new Tattoo(tattooName, description, new Image(null, imageName));
                result = tattooDao.offer(tattoo);
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Error while adding tattoo", e);
        }
    }

    @Override
    public boolean allowTattoo(String id) throws ServiceException {
        boolean result = false;
        TattooValidator tattooValidator = new TattooValidator();
        try {
            if (tattooValidator.isIdCorrect(id)) {
                long tattooId = Long.parseLong(id);
                result = tattooDao.allow(tattooId);
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Error while finding tattoos by name", e);
        }
    }

    @Override
    public boolean deleteTattoo(String id) throws ServiceException {
        boolean result = false;
        TattooValidator tattooValidator = new TattooValidator();
        try {
            if (tattooValidator.isIdCorrect(id)) {
                long tattooId = Long.parseLong(id);
                result = tattooDao.delete(tattooId);
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Error while finding tattoos by name", e);
        }
    }
}
