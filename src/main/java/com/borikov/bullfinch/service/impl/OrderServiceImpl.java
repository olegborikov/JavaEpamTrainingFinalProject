package com.borikov.bullfinch.service.impl;

import com.borikov.bullfinch.builder.OrderBuilder;
import com.borikov.bullfinch.builder.TattooBuilder;
import com.borikov.bullfinch.builder.UserBuilder;
import com.borikov.bullfinch.dao.DiscountDao;
import com.borikov.bullfinch.dao.OrderDao;
import com.borikov.bullfinch.dao.TransactionManager;
import com.borikov.bullfinch.dao.impl.DiscountDaoImpl;
import com.borikov.bullfinch.dao.impl.OrderDaoImpl;
import com.borikov.bullfinch.entity.Order;
import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.entity.User;
import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.exception.TransactionException;
import com.borikov.bullfinch.service.OrderService;
import com.borikov.bullfinch.validator.DiscountValidator;
import com.borikov.bullfinch.validator.OrderValidator;
import com.borikov.bullfinch.validator.TattooValidator;
import com.borikov.bullfinch.validator.UserValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = OrderDaoImpl.getInstance();
    private final DiscountDao discountDao = DiscountDaoImpl.getInstance();
    private final TransactionManager transactionManager =
            new TransactionManager();

    @Override
    public boolean addOrder(String date, String description,
                            String price, String tattooId, String userLogin,
                            String discountId) throws ServiceException {
        boolean result = false;
        try {
            if (OrderValidator.isDateCorrect(date)
                    && OrderValidator.isDescriptionCorrect(description)
                    && OrderValidator.isPriceCorrect(price)
                    && TattooValidator.isIdCorrect(tattooId)
                    && UserValidator.isLoginCorrect(userLogin)) {
                OrderBuilder orderBuilder = new OrderBuilder();
                orderBuilder.setDate(LocalDate.parse(date,
                        DateTimeFormatter.ISO_LOCAL_DATE));
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
                if (result) {
                    if (DiscountValidator.isIdCorrect(discountId)) {
                        long discountIdParsed = Long.parseLong(discountId);
                        discountDao.remove(discountIdParsed);
                    }
                }
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Error while adding order", e);
        }
    }

    @Override
    public boolean removeOrder(String id) throws ServiceException {
        boolean result = false;
        try {
            if (OrderValidator.isIdCorrect(id)) {
                long orderId = Long.parseLong(id);
                result = orderDao.remove(orderId);
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Error while cancel order", e);
        }
    }

    @Override
    public boolean submitOrder(String id) throws ServiceException {
        boolean result = false;
        try {
            if (OrderValidator.isIdCorrect(id)) {
                long orderId = Long.parseLong(id);
                result = transactionManager.orderSubmitProcess(orderId);
            }
            return result;
        } catch (TransactionException e) {
            throw new ServiceException("Error while cancel order", e);
        }
    }

    @Override
    public Optional<Order> findOrderById(String id) throws ServiceException {
        Optional<Order> order = Optional.empty();
        try {
            if (OrderValidator.isIdCorrect(id)) {
                long orderId = Long.parseLong(id);
                order = orderDao.findById(orderId);
            }
            return order;
        } catch (DaoException e) {
            throw new ServiceException("Error while finding " +
                    "orders by login", e);
        }
    }

    @Override
    public List<Order> getOrdersByUserLogin(String userLogin)
            throws ServiceException {
        List<Order> orders = new ArrayList<>();
        try {
            if (UserValidator.isLoginCorrect(userLogin)) {
                orders = orderDao.findByUserLogin(userLogin);
            }
            return orders;
        } catch (DaoException e) {
            throw new ServiceException("Error while finding " +
                    "orders by login", e);
        }
    }
}
