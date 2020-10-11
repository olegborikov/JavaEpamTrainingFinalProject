package com.borikov.bullfinch.builder;

import com.borikov.bullfinch.entity.Order;
import com.borikov.bullfinch.entity.Tattoo;
import com.borikov.bullfinch.entity.User;

import java.util.Date;

public class OrderBuilder {
    private Long orderId;
    private double price;
    private Date date;
    private String description;
    private User user;
    private Tattoo tattoo;

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTattoo(Tattoo tattoo) {
        this.tattoo = tattoo;
    }

    public Order getOrder() {
        return new Order(orderId, price, date, description, user, tattoo);
    }
}