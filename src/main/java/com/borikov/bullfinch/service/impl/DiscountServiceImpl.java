package com.borikov.bullfinch.service.impl;

import com.borikov.bullfinch.builder.DiscountBuilder;
import com.borikov.bullfinch.builder.UserBuilder;
import com.borikov.bullfinch.dao.DiscountDao;
import com.borikov.bullfinch.dao.impl.DiscountDaoImpl;
import com.borikov.bullfinch.entity.Discount;
import com.borikov.bullfinch.entity.User;
import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.service.DiscountService;
import com.borikov.bullfinch.validator.DiscountValidator;
import com.borikov.bullfinch.validator.OrderValidator;
import com.borikov.bullfinch.validator.UserValidator;

import java.util.ArrayList;
import java.util.List;

public class DiscountServiceImpl implements DiscountService {
    private final DiscountDao discountDao = new DiscountDaoImpl();

    @Override
    public List<Discount> getDiscountsByUserLogin(String userLogin) throws ServiceException {
        List<Discount> discounts = new ArrayList<>();
        try {
            if (UserValidator.isLoginCorrect(userLogin)) {
                discounts = discountDao.findByUserLogin(userLogin);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while finding discounts by login", e);
        }
        return discounts;
    }

    @Override
    public boolean addDiscount(String discountPercent, String userId) throws ServiceException {
        boolean result = false;
        try {
            if (DiscountValidator.isDiscountPercentCorrect(discountPercent)
                    && UserValidator.isIdCorrect(userId)) {
                int discountPercentParsed = Integer.parseInt(discountPercent);
                long userIdParsed = Long.parseLong(userId);
                DiscountBuilder discountBuilder = new DiscountBuilder();
                discountBuilder.setDiscountPercent(discountPercentParsed);
                UserBuilder userBuilder = new UserBuilder();
                userBuilder.setUserId(userIdParsed);
                User user = userBuilder.getUser();
                discountBuilder.setUser(user);
                Discount discount = discountBuilder.getDiscount();
                result = discountDao.add(discount);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while deleting discounts", e);
        }
        return result;
    }

    @Override
    public boolean removeDiscount(String discountId) throws ServiceException {
        boolean result = false;
        try {
            if (DiscountValidator.isIdCorrect(discountId)) {
                long id = Long.parseLong(discountId);
                result = discountDao.remove(id);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while deleting discounts", e);
        }
        return result;
    }
}
