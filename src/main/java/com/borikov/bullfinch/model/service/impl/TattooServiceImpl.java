package com.borikov.bullfinch.model.service.impl;

import com.borikov.bullfinch.model.builder.TattooBuilder;
import com.borikov.bullfinch.model.builder.UserBuilder;
import com.borikov.bullfinch.model.dao.TattooDao;
import com.borikov.bullfinch.model.dao.TransactionManager;
import com.borikov.bullfinch.model.dao.impl.TattooDaoImpl;
import com.borikov.bullfinch.model.entity.Image;
import com.borikov.bullfinch.model.entity.Tattoo;
import com.borikov.bullfinch.model.exception.DaoException;
import com.borikov.bullfinch.model.exception.ServiceException;
import com.borikov.bullfinch.model.exception.TransactionException;
import com.borikov.bullfinch.model.service.TattooService;
import com.borikov.bullfinch.model.validator.TattooValidator;
import com.borikov.bullfinch.model.validator.UserValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The {@code TattooServiceImpl} class represents tattoo service implementation.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class TattooServiceImpl implements TattooService {
    private final TattooDao tattooDao = TattooDaoImpl.getInstance();
    private final TransactionManager transactionManager = TransactionManager.getInstance();

    @Override
    public boolean addTattoo(String tattooName, String description, String price, String imageName,
                             String proposedLogin) throws ServiceException {
        try {
            boolean isTattooAdded = false;
            if (TattooValidator.isNameCorrect(tattooName) && TattooValidator.isDescriptionCorrect(description)
                    && TattooValidator.isPriceCorrect(price) && UserValidator.isLoginCorrect(proposedLogin)) {
                double tattooPrice = Double.parseDouble(price);
                TattooBuilder tattooBuilder = new TattooBuilder();
                tattooBuilder.setName(tattooName);
                tattooBuilder.setDescription(description);
                tattooBuilder.setPrice(tattooPrice);
                tattooBuilder.setImage(new Image(null, imageName));
                UserBuilder userBuilder = new UserBuilder();
                userBuilder.setLogin(proposedLogin);
                tattooBuilder.setUser(userBuilder.buildUser());
                Tattoo tattoo = tattooBuilder.buildTattoo();
                isTattooAdded = transactionManager.addImageAndTattoo(tattoo);
            }
            return isTattooAdded;
        } catch (TransactionException e) {
            StringBuilder message = new StringBuilder("Error while adding tattoo: ");
            message.append("tattoo name = ").append(tattooName);
            message.append(", description = ").append(description);
            message.append(", price = ").append(price);
            message.append(", image name = ").append(imageName);
            message.append(", proposed login = ").append(proposedLogin);
            throw new ServiceException(message.toString(), e);
        }
    }

    @Override
    public boolean removeTattoo(String tattooId, String imageId) throws ServiceException {
        try {
            boolean isTattooRemoved = false;
            if (TattooValidator.isIdCorrect(tattooId) && TattooValidator.isIdCorrect(imageId)) {
                long tattooIdParsed = Long.parseLong(tattooId);
                long imageIdParsed = Long.parseLong(imageId);
                isTattooRemoved = transactionManager.removeTattooAndImage(tattooIdParsed, imageIdParsed);
            }
            return isTattooRemoved;
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean editTattoo(String id, String name, String description, String price) throws ServiceException {
        try {
            boolean isTattooEdited = false;
            if (TattooValidator.isIdCorrect(id) && TattooValidator.isNameCorrect(name)
                    && TattooValidator.isDescriptionCorrect(description) && TattooValidator.isPriceCorrect(price)) {
                long tattooId = Long.parseLong(id);
                double tattooPrice = Double.parseDouble(price);
                TattooBuilder tattooBuilder = new TattooBuilder();
                tattooBuilder.setTattooId(tattooId);
                tattooBuilder.setName(name);
                tattooBuilder.setDescription(description);
                tattooBuilder.setPrice(tattooPrice);
                Tattoo tattoo = tattooBuilder.buildTattoo();
                isTattooEdited = tattooDao.update(tattoo);
            }
            return isTattooEdited;
        } catch (DaoException e) {
            StringBuilder message = new StringBuilder("Error while editing tattoo: ");
            message.append("id = ").append(id);
            message.append(", name = ").append(name);
            message.append(", description = ").append(description);
            message.append(", price = ").append(price);
            throw new ServiceException(message.toString(), e);
        }
    }

    @Override
    public boolean offerTattoo(String tattooName, String description, String price, String imageName,
                               String proposedLogin) throws ServiceException {
        try {
            boolean isTattooOffered = false;
            if (TattooValidator.isNameCorrect(tattooName) && TattooValidator.isDescriptionCorrect(description)
                    && TattooValidator.isPriceCorrect(price) && UserValidator.isLoginCorrect(proposedLogin)) {
                double tattooPrice = Double.parseDouble(price);
                TattooBuilder tattooBuilder = new TattooBuilder();
                tattooBuilder.setName(tattooName);
                tattooBuilder.setDescription(description);
                tattooBuilder.setPrice(tattooPrice);
                tattooBuilder.setImage(new Image(null, imageName));
                UserBuilder userBuilder = new UserBuilder();
                userBuilder.setLogin(proposedLogin);
                tattooBuilder.setUser(userBuilder.buildUser());
                Tattoo tattoo = tattooBuilder.buildTattoo();
                isTattooOffered = transactionManager.offerImageAndTattoo(tattoo);
            }
            return isTattooOffered;
        } catch (TransactionException e) {
            StringBuilder message = new StringBuilder("Error while offering tattoo: ");
            message.append("tattoo name = ").append(tattooName);
            message.append(", description = ").append(description);
            message.append(", price = ").append(price);
            message.append(", image name = ").append(imageName);
            message.append(", proposed login = ").append(proposedLogin);
            throw new ServiceException(message.toString(), e);
        }
    }

    @Override
    public boolean allowTattoo(String id) throws ServiceException {
        try {
            boolean isTattooAllowed = false;
            if (TattooValidator.isIdCorrect(id)) {
                long tattooId = Long.parseLong(id);
                isTattooAllowed = tattooDao.allow(tattooId);
            }
            return isTattooAllowed;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean archiveTattoo(String id) throws ServiceException {
        try {
            boolean isTattooArchived = false;
            if (TattooValidator.isIdCorrect(id)) {
                long tattooId = Long.parseLong(id);
                isTattooArchived = tattooDao.archive(tattooId);
            }
            return isTattooArchived;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean unarchiveTattoo(String id) throws ServiceException {
        try {
            boolean isTattooUnarchived = false;
            if (TattooValidator.isIdCorrect(id)) {
                long tattooId = Long.parseLong(id);
                isTattooUnarchived = tattooDao.unarchive(tattooId);
            }
            return isTattooUnarchived;
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
            List<Tattoo> tattoos = new ArrayList<>();
            if (TattooValidator.isNameSubstringCorrect(nameSubstring)) {
                tattoos = tattooDao.findByNameSubstring(nameSubstring);
            }
            return tattoos;
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
            List<Tattoo> tattoos = new ArrayList<>();
            if (TattooValidator.isNameSubstringCorrect(nameSubstring)) {
                tattoos = tattooDao.findByNameSubstringCatalog(nameSubstring);
            }
            return tattoos;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
