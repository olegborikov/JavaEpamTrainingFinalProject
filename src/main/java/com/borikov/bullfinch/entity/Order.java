package com.borikov.bullfinch.entity;

public class Order {
    private Long orderId;
    private double price;
    private long date;// TODO: 16.09.2020 which type?
    private String description;
    private User user;
    private Tattoo tattoo;

    public Order(Long orderId, double price, long date,
                 String description, User user, Tattoo tattoo) {
        this.orderId = orderId;
        this.price = price;
        this.date = date;
        this.description = description;
        this.user = user;
        this.tattoo = tattoo;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tattoo getTattoo() {
        return tattoo;
    }

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
        if (date != order.date) {
            return false;
        }
        if (orderId != null ? !orderId.equals(order.orderId) : order.orderId != null) {
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
        result = 31 * result + (int) (date ^ (date >>> 32));
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
