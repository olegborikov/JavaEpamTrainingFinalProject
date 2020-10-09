package com.borikov.bullfinch.builder;

import com.borikov.bullfinch.entity.Image;
import com.borikov.bullfinch.entity.Tattoo;

public class TattooBuilder {
    private Long tattooId;
    private String name;
    private String description;
    private double price;
    private byte rating;
    private boolean isAllowed;
    private boolean isArchived;
    private Image image;

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

    public void setRating(byte rating) {
        this.rating = rating;
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

    public Tattoo getTattoo() {
        return new Tattoo(tattooId, name, description, price,
                rating, isAllowed, isArchived, image);
    }
}
