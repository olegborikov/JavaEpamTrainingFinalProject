package com.borikov.bullfinch.model.service.impl;

import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.exception.TransactionException;
import com.borikov.bullfinch.model.builder.TattooBuilder;
import com.borikov.bullfinch.model.builder.UserBuilder;
import com.borikov.bullfinch.model.dao.TattooDao;
import com.borikov.bullfinch.model.dao.TransactionManager;
import com.borikov.bullfinch.model.dao.impl.TattooDaoImpl;
import com.borikov.bullfinch.model.entity.Image;
import com.borikov.bullfinch.model.entity.Tattoo;
import com.borikov.bullfinch.model.service.TattooService;
import com.borikov.bullfinch.model.validator.TattooValidator;

import java.util.List;
import java.util.Optional;

public class TattooServiceImpl implements TattooService {
    private final TattooDao tattooDao = TattooDaoImpl.getInstance();
    private final TransactionManager transactionManager = new TransactionManager();

    @Override
    public boolean addTattoo(String tattooName, String description, String price, String imageName,
                             String proposedLogin) throws ServiceException {
        try {
            boolean result = false;
            if (TattooValidator.isNameCorrect(tattooName) && TattooValidator.isDescriptionCorrect(description)
                    && TattooValidator.isPriceCorrect(price)) {
                double tattooPrice = Double.parseDouble(price);
                TattooBuilder tattooBuilder = new TattooBuilder();
                tattooBuilder.setName(tattooName);
                tattooBuilder.setDescription(description);
                tattooBuilder.setPrice(tattooPrice);
                tattooBuilder.setImage(new Image(null, imageName));
                UserBuilder userBuilder = new UserBuilder();
                userBuilder.setLogin(proposedLogin);
                tattooBuilder.setUser(userBuilder.getUser());
                Tattoo tattoo = tattooBuilder.getTattoo();
                result = transactionManager.addImageAndTattoo(tattoo);
            }
            return result;
        } catch (TransactionException e) {
            throw new ServiceException("Error while adding tattoo: tattoo name = " + tattooName
                    + ", description = " + description + ", price = " + price + ", image name = "
                    + imageName + ", proposed login = " + proposedLogin, e);
        }
    }

    @Override
    public boolean removeTattoo(String tattooId, String imageId) throws ServiceException {
        try {
            boolean result = false;
            if (TattooValidator.isIdCorrect(tattooId) && TattooValidator.isIdCorrect(imageId)) {
                long tattooIdParsed = Long.parseLong(tattooId);
                long imageIdParsed = Long.parseLong(imageId);
                result = transactionManager.removeTattooAndImage(tattooIdParsed, imageIdParsed);
            }
            return result;
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean editTattoo(String id, String name, String description, String price) throws ServiceException {
        try {
            boolean result = false;
            if (TattooValidator.isIdCorrect(id) && TattooValidator.isNameCorrect(name)
                    && TattooValidator.isDescriptionCorrect(description) && TattooValidator.isPriceCorrect(price)) {
                long tattooId = Long.parseLong(id);
                double tattooPrice = Double.parseDouble(price);
                TattooBuilder tattooBuilder = new TattooBuilder();
                tattooBuilder.setTattooId(tattooId);
                tattooBuilder.setName(name);
                tattooBuilder.setDescription(description);
                tattooBuilder.setPrice(tattooPrice);
                Tattoo tattoo = tattooBuilder.getTattoo();
                result = tattooDao.update(tattoo);
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Error while editing tattoo: id = " + id + ", name = "
                    + name + ", description = " + description + ", price = " + price, e);
        }
    }

    @Override
    public boolean offerTattoo(String tattooName, String description, String price, String imageName,
                               String proposedLogin) throws ServiceException {
        try {
            boolean result = false;
            if (TattooValidator.isNameCorrect(tattooName) && TattooValidator.isDescriptionCorrect(description)
                    && TattooValidator.isPriceCorrect(price)) {
                double tattooPrice = Double.parseDouble(price);
                TattooBuilder tattooBuilder = new TattooBuilder();
                tattooBuilder.setName(tattooName);
                tattooBuilder.setDescription(description);
                tattooBuilder.setPrice(tattooPrice);
                tattooBuilder.setImage(new Image(null, imageName));
                UserBuilder userBuilder = new UserBuilder();
                userBuilder.setLogin(proposedLogin);
                tattooBuilder.setUser(userBuilder.getUser());
                Tattoo tattoo = tattooBuilder.getTattoo();
                result = transactionManager.offerImageAndTattoo(tattoo);
            }
            return result;
        } catch (TransactionException e) {
            throw new ServiceException("Error while offering tattoo: tattoo name = " + tattooName
                    + ", description = " + description + ", price = " + price + ", image name = "
                    + imageName + ", proposed login = " + proposedLogin, e);
        }
    }

    @Override
    public boolean allowTattoo(String id) throws ServiceException {
        try {
            boolean result = false;
            if (TattooValidator.isIdCorrect(id)) {
                long tattooId = Long.parseLong(id);
                result = tattooDao.allow(tattooId);
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean archiveTattoo(String id) throws ServiceException {
        try {
            boolean result = false;
            if (TattooValidator.isIdCorrect(id)) {
                long tattooId = Long.parseLong(id);
                result = tattooDao.archive(tattooId);
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean unarchiveTattoo(String id) throws ServiceException {
        try {
            boolean result = false;
            if (TattooValidator.isIdCorrect(id)) {
                long tattooId = Long.parseLong(id);
                result = tattooDao.unarchive(tattooId);
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Tattoo> findAllTattoos() throws ServiceException {
        try {
            return tattooDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Tattoo> findTattooById(String id) throws ServiceException {
        try {
            Optional<Tattoo> tattoo = Optional.empty();
            if (TattooValidator.isIdCorrect(id)) {
                long tattooId = Long.parseLong(id);
                tattoo = tattooDao.findById(tattooId);
            }
            return tattoo;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Tattoo> findTattoosByNameSubstring(String nameSubstring) throws ServiceException {
        try {
            return tattooDao.findByNameSubstring(nameSubstring);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Tattoo> findTattoosByAllowed(boolean isAllowed) throws ServiceException {
        try {
            return tattooDao.findByAllowed(isAllowed);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Tattoo> findTattoosByArchived(boolean isArchived) throws ServiceException {
        try {
            return tattooDao.findByArchived(isArchived);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Tattoo> findAllTattoosCatalog() throws ServiceException {
        try {
            return tattooDao.findAllCatalog();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Tattoo> findTattooByIdCatalog(String id) throws ServiceException {
        try {
            Optional<Tattoo> tattoo = Optional.empty();
            if (TattooValidator.isIdCorrect(id)) {
                long tattooId = Long.parseLong(id);
                tattoo = tattooDao.findByIdCatalog(tattooId);
            }
            return tattoo;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Tattoo> findTattoosByNameSubstringCatalog(String nameSubstring) throws ServiceException {
        try {
            return tattooDao.findByNameSubstringCatalog(nameSubstring);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
