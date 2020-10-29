package com.borikov.bullfinch.model.builder;

import com.borikov.bullfinch.model.entity.Discount;
import com.borikov.bullfinch.model.entity.User;

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
