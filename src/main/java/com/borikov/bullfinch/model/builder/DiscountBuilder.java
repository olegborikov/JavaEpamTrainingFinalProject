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
    private int percent;
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
     * Sets percent.
     *
     * @param percent the percent
     */
    public void setPercent(int percent) {
        this.percent = percent;
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
     * Build discount.
     *
     * @return the discount
     */
    public Discount buildDiscount() {
        return new Discount(discountId, percent, user);
    }
}
