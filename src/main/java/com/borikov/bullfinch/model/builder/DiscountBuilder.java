package com.borikov.bullfinch.model.builder;

import com.borikov.bullfinch.model.entity.Discount;
import com.borikov.bullfinch.model.entity.User;

/**
 * The {@code DiscountBuilder} class represents discount builder.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class DiscountBuilder {
    private Long discountId;
    private int discountPercent;
    private User user;

    /**
     * Sets discount id.
     *
     * @param discountId the discount id
     */
    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
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
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets discount.
     *
     * @return the discount
     */
    public Discount getDiscount() {
        return new Discount(discountId, discountPercent, user);
    }
}
