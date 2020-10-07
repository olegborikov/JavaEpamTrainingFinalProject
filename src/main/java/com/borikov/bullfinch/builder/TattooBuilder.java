package com.borikov.bullfinch.builder;

import com.borikov.bullfinch.entity.Image;

public interface TattooBuilder {
    void setTattooId(Long tattooId);

    void setName(String name);

    void setDescription(String description);

    void setPrice(double price);

    void setRating(byte rating);

    void setAllowed(boolean allowed);

    void setArchived(boolean archived);

    void setImage(Image image);
}
