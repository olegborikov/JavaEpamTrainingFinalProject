package com.borikov.bullfinch.model.service.impl;

import com.borikov.bullfinch.model.builder.DiscountBuilder;
import com.borikov.bullfinch.model.builder.UserBuilder;
import com.borikov.bullfinch.model.dao.DiscountDao;
import com.borikov.bullfinch.model.dao.impl.DiscountDaoImpl;
import com.borikov.bullfinch.model.entity.Discount;
import com.borikov.bullfinch.model.entity.User;
import com.borikov.bullfinch.model.exception.DaoException;
import com.borikov.bullfinch.model.exception.ServiceException;
import com.borikov.bullfinch.model.service.DiscountService;
import com.borikov.bullfinch.model.validator.DiscountValidator;
import com.borikov.bullfinch.model.validator.UserValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code DiscountServiceImpl} class represents discount service implementation.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class DiscountServiceImpl implements DiscountService {
    private final DiscountDao discountDao = DiscountDaoImpl.getInstance();

    @Override
    public boolean addDiscount(String percent, String userId) throws ServiceException {
        try {
            boolean isDiscountAdded = false;
            if (DiscountValidator.isPercentCorrect(percent) && UserValidator.isIdCorrect(userId)) {
                int percentParsed = Integer.parseInt(percent);
                long userIdParsed = Long.parseLong(userId);
                DiscountBuilder discountBuilder = new DiscountBuilder();
                discountBuilder.setPercent(percentParsed);
                UserBuilder userBuilder = new UserBuilder();
                userBuilder.setUserId(userIdParsed);
                User user = userBuilder.buildUser();
                discountBuilder.setUser(user);
                Discount discount = discountBuilder.buildDiscount();
                isDiscountAdded = discountDao.add(discount);
            }
            return isDiscountAdded;
        } catch (DaoException e) {
            StringBuilder message = new StringBuilder("Error while adding discount: ");
            message.append("percent = ").append(percent);
            message.append(", user id = ").append(userId);
            throw new ServiceException(message.toString(), e);
        }
    }

    @Override
    public boolean removeDiscount(String discountId) throws ServiceException {
        try {
            boolean isDiscountRemoved = false;
            if (DiscountValidator.isIdCorrect(discountId)) {
                long id = Long.parseLong(discountId);
                isDiscountRemoved = discountDao.remove(id);
            }
            return isDiscountRemoved;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Discount> findDiscountsByUserLogin(String userLogin) throws ServiceException {
        try {
            List<Discount> discounts = new ArrayList<>();
            if (UserValidator.isLoginCorrect(userLogin)) {
                discounts = discountDao.findByUserLogin(userLogin);
            }
            return discounts;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
