package com.borikov.bullfinch.model.builder;

import com.borikov.bullfinch.model.entity.Image;
import com.borikov.bullfinch.model.entity.Tattoo;
import com.borikov.bullfinch.model.entity.User;

public class TattooBuilder {
    private Long tattooId;
    private String name;
    private String description;
    private double price;
    private boolean isAllowed;
    private boolean isArchived;
    private Image image;
    private User user;

    public void setTattooId(Long tattooId) {
        this.tattooId = tattooId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAllowed(boolean allowed) {
        isAllowed = allowed;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tattoo getTattoo() {
        return new Tattoo(tattooId, name, description, price, isAllowed, isArchived, image, user);
    }
}

