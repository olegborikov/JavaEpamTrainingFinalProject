package com.borikov.bullfinch.model.builder;

import com.borikov.bullfinch.model.entity.Image;
import com.borikov.bullfinch.model.entity.Tattoo;
import com.borikov.bullfinch.model.entity.User;

/**
 * The {@code TattooBuilder} class represents tattoo builder.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class TattooBuilder {
    private Long tattooId;
    private String name;
    private String description;
    private double price;
    private boolean isAllowed;
    private boolean isArchived;
    private Image image;
    private User user;

    /**
     * Sets tattoo id.
     *
     * @param tattooId the tattoo id
     */
    public void setTattooId(Long tattooId) {
        this.tattooId = tattooId;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
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
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets allowed.
     *
     * @param allowed the allowed
     */
    public void setAllowed(boolean allowed) {
        isAllowed = allowed;
    }

    /**
     * Sets archived.
     *
     * @param archived the archived
     */
    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(Image image) {
        this.image = image;
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
     * Build tattoo.
     *
     * @return the tattoo
     */
    public Tattoo buildTattoo() {
        return new Tattoo(tattooId, name, description, price, isAllowed, isArchived, image, user);
    }
}

