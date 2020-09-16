package com.borikov.bullfinch.entity;

public class Discount {
    private Long discountId;
    private byte discountPercent;
    private long endDate;// TODO: 16.09.2020 which type?
    private User user;

    public Discount(Long discountId, byte discountPercent, long endDate, User user) {
        this.discountId = discountId;
        this.discountPercent = discountPercent;
        this.endDate = endDate;
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

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
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
        if (endDate != discount.endDate) {
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
        result = 31 * result + (int) (endDate ^ (endDate >>> 32));
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Discount{");
        sb.append("discountId=").append(discountId);
        sb.append(", discountPercent=").append(discountPercent);
        sb.append(", endDate=").append(endDate);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
