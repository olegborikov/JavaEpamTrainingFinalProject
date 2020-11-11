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
     * The value is used for discount percent storage.
     */
    private int discountPercent;

    /**
     * The value is used for user storage.
     */
    private User user;

    /**
     * Instantiates a new Discount.
     *
     * @param discountId      the discount id
     * @param discountPercent the discount percent
     * @param user            the user
     */
    public Discount(Long discountId, int discountPercent, User user) {
        this.discountId = discountId;
        this.discountPercent = discountPercent;
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
     * Gets discount percent.
     *
     * @return the discount percent
     */
    public int getDiscountPercent() {
        return discountPercent;
    }

    /**
     * Sets discount percent.
     *
     * @param discountPercent the discount percent
     */
    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
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
        if (discountPercent != discount.discountPercent) {
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
        hashCode = 31 * hashCode + discountPercent;
        hashCode = 31 * hashCode + (user != null ? user.hashCode() : 0);
        return hashCode;
    }

    @Override
    public String toString() {
        final StringBuilder stringRepresentation = new StringBuilder("Discount{");
        stringRepresentation.append("discountId=").append(discountId);
        stringRepresentation.append(", discountPercent=").append(discountPercent);
        stringRepresentation.append(", user=").append(user);
        stringRepresentation.append('}');
        return stringRepresentation.toString();
    }
}
