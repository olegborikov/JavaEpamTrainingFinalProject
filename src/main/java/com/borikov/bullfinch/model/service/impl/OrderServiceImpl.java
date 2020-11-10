package com.borikov.bullfinch.model.service.impl;

import com.borikov.bullfinch.model.builder.OrderBuilder;
import com.borikov.bullfinch.model.builder.TattooBuilder;
import com.borikov.bullfinch.model.builder.UserBuilder;
import com.borikov.bullfinch.model.dao.DiscountDao;
import com.borikov.bullfinch.model.dao.OrderDao;
import com.borikov.bullfinch.model.dao.TransactionManager;
import com.borikov.bullfinch.model.dao.impl.DiscountDaoImpl;
import com.borikov.bullfinch.model.dao.impl.OrderDaoImpl;
import com.borikov.bullfinch.model.entity.Order;
import com.borikov.bullfinch.model.entity.Tattoo;
import com.borikov.bullfinch.model.entity.User;
import com.borikov.bullfinch.model.exception.DaoException;
import com.borikov.bullfinch.model.exception.ServiceException;
import com.borikov.bullfinch.model.exception.TransactionException;
import com.borikov.bullfinch.model.service.OrderService;
import com.borikov.bullfinch.model.validator.DiscountValidator;
import com.borikov.bullfinch.model.validator.OrderValidator;
import com.borikov.bullfinch.model.validator.TattooValidator;
import com.borikov.bullfinch.model.validator.UserValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The {@code OrderServiceImpl} class represents order service implementation.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = OrderDaoImpl.getInstance();
    private final DiscountDao discountDao = DiscountDaoImpl.getInstance();
    private final TransactionManager transactionManager = TransactionManager.getInstance();

    @Override
    public boolean addOrder(String date, String description, String price, String tattooId, String userLogin,
                            String discountId) throws ServiceException {
        try {
            boolean isOrderAdded = false;
            if (OrderValidator.isDateCorrect(date) && OrderValidator.isDescriptionCorrect(description)
                    && OrderValidator.isPriceCorrect(price) && TattooValidator.isIdCorrect(tattooId)
                    && UserValidator.isLoginCorrect(userLogin)) {
                OrderBuilder orderBuilder = new OrderBuilder();
                orderBuilder.setDate(LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE));
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
                isOrderAdded = orderDao.add(order);
                if (isOrderAdded) {
                    if (DiscountValidator.isIdCorrect(discountId)) {
                        long discountIdParsed = Long.parseLong(discountId);
                        discountDao.remove(discountIdParsed);
                    }
                }
            }
            return isOrderAdded;
        } catch (DaoException e) {
            StringBuilder sb = new StringBuilder("Error while adding order: ");
            sb.append("date = ").append(date);
            sb.append(", description = ").append(description);
            sb.append(", price = ").append(price);
            sb.append(", tattoo id = ").append(tattooId);
            sb.append(", user login = ").append(userLogin);
            sb.append(", discount id = ").append(discountId);
            throw new ServiceException(sb.toString(), e);
        }
    }

    @Override
    public boolean removeOrder(String id) throws ServiceException {
        try {
            boolean isOrderRemoved = false;
            if (OrderValidator.isIdCorrect(id)) {
                long orderId = Long.parseLong(id);
                isOrderRemoved = orderDao.remove(orderId);
            }
            return isOrderRemoved;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean submitOrder(String id) throws ServiceException {
        try {
            boolean isOrderSubmited = false;
            if (OrderValidator.isIdCorrect(id)) {
                long orderId = Long.parseLong(id);
                isOrderSubmited = transactionManager.orderSubmitProcess(orderId);
            }
            return isOrderSubmited;
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findAllOrders() throws ServiceException {
        try {
            return orderDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Order> findOrderById(String id) throws ServiceException {
        try {
            Optional<Order> order = Optional.empty();
            if (OrderValidator.isIdCorrect(id)) {
                long orderId = Long.parseLong(id);
                order = orderDao.findById(orderId);
            }
            return order;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findOrdersByDates(String beginDate, String endDate) throws ServiceException {
        try {
            List<Order> orders = new ArrayList<>();
            if (OrderValidator.isDateCorrect(beginDate) && OrderValidator.isDateCorrect(endDate)) {
                LocalDate beginDateParsed = LocalDate.parse(beginDate, DateTimeFormatter.ISO_LOCAL_DATE);
                LocalDate endDateParsed = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
                if (beginDateParsed.compareTo(endDateParsed) < 0) {
                    orders = orderDao.findByDates(beginDateParsed, endDateParsed);
                }
            }
            return orders;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findOrdersByUserLogin(String userLogin) throws ServiceException {
        try {
            List<Order> orders = new ArrayList<>();
            if (UserValidator.isLoginCorrect(userLogin)) {
                orders = orderDao.findByUserLogin(userLogin);
            }
            return orders;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
