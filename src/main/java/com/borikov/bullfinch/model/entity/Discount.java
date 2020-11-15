package com.borikov.bullfinch.model.entity;

/**
 * The {@code Discount} class represents discount entity.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class Discount {
    /**
     * The value is used for discount id storage.
     */
    private Long discountId;

    /**
     * The value is used for percent storage.
     */
    private int percent;

    /**
     * The value is used for user storage.
     */
    private User user;

    /**
     * Instantiates a new Discount.
     *
     * @param discountId the discount id
     * @param percent    the percent
     * @param user       the user
     */
    public Discount(Long discountId, int percent, User user) {
        this.discountId = discountId;
        this.percent = percent;
        this.user = user;
    }

    /**
     * Gets discount id.
     *
     * @return the discount id
     */
    public Long getDiscountId() {
        return discountId;
    }

    /**
     * Sets discount id.
     *
     * @param discountId the discount id
     */
    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    /**
     * Gets percent.
     *
     * @return the percent
     */
    public int getPercent() {
        return percent;
    }

    /**
     * Sets percent.
     *
     * @param percent the percent
     */
    public void setPercent(int percent) {
        this.percent = percent;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Discount discount = (Discount) o;
        if (percent != discount.percent) {
            return false;
        }
        if (discountId != null ? !discountId.equals(discount.discountId) : discount.discountId != null) {
            return false;
        }
        return user != null ? user.equals(discount.user) : discount.user == null;
    }

    @Override
    public int hashCode() {
        int hashCode = discountId != null ? discountId.hashCode() : 0;
        hashCode = 31 * hashCode + percent;
        hashCode = 31 * hashCode + (user != null ? user.hashCode() : 0);
        return hashCode;
    }

    @Override
    public String toString() {
        final StringBuilder stringRepresentation = new StringBuilder("Discount{");
        stringRepresentation.append("discountId=").append(discountId);
        stringRepresentation.append(", percent=").append(percent);
        stringRepresentation.append(", user=").append(user);
        stringRepresentation.append('}');
        return stringRepresentation.toString();
    }
}
