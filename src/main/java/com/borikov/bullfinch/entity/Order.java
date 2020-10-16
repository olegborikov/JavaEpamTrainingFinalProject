package com.borikov.bullfinch.entity;

import java.time.LocalDate;

/**
 * The {@code Order} class represents tattoo_order entity
 *
 * @author Oleg Borikov
 * @version 1.0
 * @since 2020-10-16
 */
public class Order {
    /**
     * The value is used for order id storage.
     */
    private Long orderId;

    /**
     * The value is used for price storage.
     */
    private double price;

    /**
     * The value is used for date storage.
     */
    private LocalDate date;

    /**
     * The value is used for description storage.
     */
    private String description;

    /**
     * The value is used for user storage.
     */
    private User user;

    /**
     * The value is used for tattoo storage.
     */
    private Tattoo tattoo;

    /**
     * Instantiates a new Order.
     *
     * @param orderId     the order id
     * @param price       the price
     * @param date        the date
     * @param description the description
     * @param user        the user
     * @param tattoo      the tattoo
     */
    public Order(Long orderId, double price, LocalDate date,
                 String description, User user, Tattoo tattoo) {
        this.orderId = orderId;
        this.price = price;
        this.date = date;
        this.description = description;
        this.user = user;
        this.tattoo = tattoo;
    }

    /**
     * Gets order id.
     *
     * @return the order id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * Sets order id.
     *
     * @param orderId the order id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
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
     * Gets date.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
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
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
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
     * Gets tattoo.
     *
     * @return the tattoo
     */
    public Tattoo getTattoo() {
        return tattoo;
    }

    /**
     * Sets tattoo.
     *
     * @param tattoo the tattoo
     */
    public void setTattoo(Tattoo tattoo) {
        this.tattoo = tattoo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        if (Double.compare(order.price, price) != 0) {
            return false;
        }
        if (orderId != null ? !orderId.equals(order.orderId) : order.orderId != null) {
            return false;
        }
        if (date != null ? !date.equals(order.date) : order.date != null) {
            return false;
        }
        if (description != null ? !description.equals(order.description)
                : order.description != null) {
            return false;
        }
        if (user != null ? !user.equals(order.user) : order.user != null) {
            return false;
        }
        return tattoo != null ? tattoo.equals(order.tattoo) : order.tattoo == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = orderId != null ? orderId.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (tattoo != null ? tattoo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", price=").append(price);
        sb.append(", date=").append(date);
        sb.append(", description='").append(description).append('\'');
        sb.append(", user=").append(user);
        sb.append(", tattoo=").append(tattoo);
        sb.append('}');
        return sb.toString();
    }
}
