package com.borikov.bullfinch.model.builder;

import com.borikov.bullfinch.model.entity.Order;
import com.borikov.bullfinch.model.entity.Tattoo;
import com.borikov.bullfinch.model.entity.User;

import java.time.LocalDate;

/**
 * The type Order builder.
 */
public class OrderBuilder {
    private Long orderId;
    private double price;
    private LocalDate date;
    private String description;
    private boolean isConfirmed;
    private User user;
    private Tattoo tattoo;

    /**
     * Sets order id.
     *
     * @param orderId the order id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets confirmed.
     *
     * @param confirmed the confirmed
     */
    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Sets tattoo.
     *
     * @param tattoo the tattoo
     */
    public void setTattoo(Tattoo tattoo) {
        this.tattoo = tattoo;
    }

    /**
     * Gets order.
     *
     * @return the order
     */
    public Order getOrder() {
        return new Order(orderId, price, date, description, isConfirmed, user, tattoo);
    }
}
