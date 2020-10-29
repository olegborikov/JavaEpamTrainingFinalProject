package com.borikov.bullfinch.model.service.impl;

import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.model.builder.DiscountBuilder;
import com.borikov.bullfinch.model.builder.UserBuilder;
import com.borikov.bullfinch.model.dao.DiscountDao;
import com.borikov.bullfinch.model.dao.impl.DiscountDaoImpl;
import com.borikov.bullfinch.model.entity.Discount;
import com.borikov.bullfinch.model.entity.User;
import com.borikov.bullfinch.model.service.DiscountService;
import com.borikov.bullfinch.model.validator.DiscountValidator;
import com.borikov.bullfinch.model.validator.UserValidator;

import java.util.ArrayList;
import java.util.List;

public class DiscountServiceImpl implements DiscountService {
    private final DiscountDao discountDao = DiscountDaoImpl.getInstance();

    @Override
    public boolean addDiscount(String discountPercent, String userId) throws ServiceException {
        try {
            boolean result = false;
            if (DiscountValidator.isDiscountPercentCorrect(discountPercent) && UserValidator.isIdCorrect(userId)) {
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
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Error while adding discount: discount percent = "
                    + discountPercent + ", user id = " + userId, e);
        }
    }

    @Override
    public boolean removeDiscount(String discountId) throws ServiceException {
        try {
            boolean result = false;
            if (DiscountValidator.isIdCorrect(discountId)) {
                long id = Long.parseLong(discountId);
                result = discountDao.remove(id);
            }
            return result;
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
