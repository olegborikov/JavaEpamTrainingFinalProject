package com.borikov.bullfinch.service.impl;

import com.borikov.bullfinch.builder.OrderBuilder;
import com.borikov.bullfinch.builder.TattooBuilder;
import com.borikov.bullfinch.builder.UserBuilder;
import com.borikov.bullfinch.dao.OrderDao;
import com.borikov.bullfinch.dao.impl.OrderDaoImpl;
import com.borikov.bullfinch.entity.Order;
import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.entity.User;
import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.service.OrderService;
import com.borikov.bullfinch.validator.impl.OrderValidator;
import com.borikov.bullfinch.validator.impl.TattooValidator;
import com.borikov.bullfinch.validator.impl.UserValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = new OrderDaoImpl();
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public boolean addOrder(String date, String description, String price,
                            String tattooId, String userLogin) throws ServiceException {
        boolean result = false;
        OrderValidator orderValidator = new OrderValidator();
        TattooValidator tattooValidator = new TattooValidator();
        UserValidator userValidator = new UserValidator();
        try {
            if (orderValidator.isDateCorrect(date)
                    && orderValidator.isDescriptionCorrect(description)
                    && orderValidator.isPriceCorrect(price)
                    && tattooValidator.isIdCorrect(tattooId)
                    && userValidator.isLoginCorrect(userLogin)) {
                OrderBuilder orderBuilder = new OrderBuilder();
                orderBuilder.setDate(simpleDateFormat.parse(date));
                orderBuilder.setDescription(description);
                orderBuilder.setPrice(Double.parseDouble(price));
                TattooBuilder tattooBuilder = new TattooBuilder();
                tattooBuilder.setTattooId(Long.parseLong(tattooId));
                Tattoo tattoo = tattooBuilder.getTattoo();
                orderBuilder.setTattoo(tattoo);
                UserBuilder userBuilder = new UserBuilder();
                userBuilder.setLogin(userLogin);
                User user = userBuilder.getUser();
                orderBuilder.setUser(user);
                Order order = orderBuilder.getOrder();
                result = orderDao.add(order);
            }
            return result;
        } catch (DaoException | ParseException e) {
            throw new ServiceException("Error while adding order", e);
        }
    }

    @Override
    public List<Order> getOrdersByUserLogin(String userLogin) throws ServiceException {
        List<Order> orders = new ArrayList<>();
        UserValidator userValidator = new UserValidator();
        try {
            if (userValidator.isLoginCorrect(userLogin)) {
               orders = orderDao.findByUserLogin(userLogin);
            }
            return orders;
        } catch (DaoException e) {
            throw new ServiceException("Error while finding orders by user login", e);
        }
    }
}
