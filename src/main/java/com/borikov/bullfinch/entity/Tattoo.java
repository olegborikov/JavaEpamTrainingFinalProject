package com.borikov.bullfinch.entity;

public class Tattoo {
    private Long tattooId;
    private String name;
    private String description;
    private double price;
    private byte rating;
    private boolean isAllowed;
    private boolean isArchived;
    private Image image;
    private User user;

    public Tattoo() {
    }

    public Tattoo(Long tattooId, String name, String description, double price,
                  byte rating, boolean isAllowed, boolean isArchived, Image image, User user) {
        this.tattooId = tattooId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.isAllowed = isAllowed;
        this.isArchived = isArchived;
        this.image = image;
        this.user = user;
    }

    public Long getTattooId() {
        return tattooId;
    }

    public void setTattooId(Long tattooId) {
        this.tattooId = tattooId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    public boolean isAllowed() {
        return isAllowed;
    }

    public void setAllowed(boolean allowed) {
        isAllowed = allowed;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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
        Tattoo tattoo = (Tattoo) o;
        if (Double.compare(tattoo.price, price) != 0) {
            return false;
        }
        if (rating != tattoo.rating) {
            return false;
        }
        if (isAllowed != tattoo.isAllowed) {
            return false;
        }
        if (isArchived != tattoo.isArchived) {
            return false;
        }
        if (tattooId != null ? !tattooId.equals(tattoo.tattooId) : tattoo.tattooId != null) {
            return false;
        }
        if (name != null ? !name.equals(tattoo.name) : tattoo.name != null) {
            return false;
        }
        if (description != null ? !description.equals(tattoo.description)
                : tattoo.description != null) {
            return false;
        }
        if (image != null ? !image.equals(tattoo.image) : tattoo.image != null) {
            return false;
        }
        return user != null ? user.equals(tattoo.user) : tattoo.user == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = tattooId != null ? tattooId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) rating;
        result = 31 * result + (isAllowed ? 1 : 0);
        result = 31 * result + (isArchived ? 1 : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tattoo{");
        sb.append("tattooId=").append(tattooId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", rating=").append(rating);
        sb.append(", isAllowed=").append(isAllowed);
        sb.append(", isArchived=").append(isArchived);
        sb.append(", image=").append(image);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
