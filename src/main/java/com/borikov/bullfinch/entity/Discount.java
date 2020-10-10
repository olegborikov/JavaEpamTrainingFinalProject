package com.borikov.bullfinch.entity;

public class Discount {
    private Long discountId;
    private byte discountPercent;
    private User user;

    public Discount(Long discountId, byte discountPercent, User user) {
        this.discountId = discountId;
        this.discountPercent = discountPercent;
        this.user = user;
    }

    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public byte getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(byte discountPercent) {
        this.discountPercent = discountPercent;
    }


    public User getUser() {
        return user;
    }

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
        if (discountId != null ? !discountId.equals(discount.discountId)
                : discount.discountId != null) {
            return false;
        }
        return user != null ? user.equals(discount.user) : discount.user == null;
    }

    @Override
    public int hashCode() {
        int result = discountId != null ? discountId.hashCode() : 0;
        result = 31 * result + (int) discountPercent;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Discount{");
        sb.append("discountId=").append(discountId);
        sb.append(", discountPercent=").append(discountPercent);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
