package com.borikov.bullfinch.builder.impl;

import com.borikov.bullfinch.builder.TattooBuilder;
import com.borikov.bullfinch.entity.Image;
import com.borikov.bullfinch.entity.Tattoo;

public class TattooBuilderImpl implements TattooBuilder {
    private Long tattooId;
    private String name;
    private String description;
    private double price;
    private byte rating;
    private boolean isAllowed;
    private boolean isArchived;
    private Image image;

    @Override
    public void setTattooId(Long tattooId) {
        this.tattooId = tattooId;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void setRating(byte rating) {
        this.rating = rating;
    }

    @Override
    public void setAllowed(boolean allowed) {
        isAllowed = allowed;
    }

    @Override
    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
    }

    public Tattoo getTattoo() {
        return new Tattoo(tattooId, name, description, price,
                rating, isAllowed, isArchived, image);
    }
}

