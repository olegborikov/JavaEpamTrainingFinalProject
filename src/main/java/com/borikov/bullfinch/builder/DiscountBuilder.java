package com.borikov.bullfinch.builder;

import com.borikov.bullfinch.entity.Discount;
import com.borikov.bullfinch.entity.User;

public class DiscountBuilder {
    private Long discountId;
    private int discountPercent;
    private User user;

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Discount getDiscount() {
        return new Discount(discountId, discountPercent, user);
    }
}
