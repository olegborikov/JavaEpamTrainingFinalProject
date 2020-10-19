package com.borikov.bullfinch.service.impl;

import com.borikov.bullfinch.dao.DiscountDao;
import com.borikov.bullfinch.dao.impl.DiscountDaoImpl;
import com.borikov.bullfinch.entity.Discount;
import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.service.DiscountService;
import com.borikov.bullfinch.validator.impl.UserValidator;

import java.util.ArrayList;
import java.util.List;

public class DiscountServiceImpl implements DiscountService {
    private final DiscountDao discountDao = new DiscountDaoImpl();

    @Override
    public List<Discount> getDiscountsByUserLogin(String userLogin) throws ServiceException {
        List<Discount> discounts = new ArrayList<>();
        UserValidator userValidator = new UserValidator();
        try {
            if (userValidator.isLoginCorrect(userLogin)) {
                discounts = discountDao.findByUserLogin(userLogin);
            }
        }catch (DaoException e){
            throw new ServiceException("Error while finding discounts by login", e);
        }
        return discounts;
    }
}
