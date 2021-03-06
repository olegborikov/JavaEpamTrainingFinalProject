package com.borikov.bullfinch.model.entity;

/**
 * The {@code Tattoo} class represents tattoo entity.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class Tattoo {
    /**
     * The value is used for tattoo id storage.
     */
    private Long tattooId;

    /**
     * The value is used for name storage.
     */
    private String name;

    /**
     * The value is used for description storage.
     */
    private String description;

    /**
     * The value is used for price storage.
     */
    private double price;

    /**
     * The value is used for allow storage.
     */
    private boolean isAllowed;

    /**
     * The value is used for archived storage.
     */
    private boolean isArchived;

    /**
     * The value is used for image storage.
     */
    private Image image;

    /**
     * The value is used for user storage.
     */
    private User user;

    /**
     * Instantiates a new Tattoo.
     *
     * @param tattooId    the tattoo id
     * @param name        the name
     * @param description the description
     * @param price       the price
     * @param isAllowed   the is allowed
     * @param isArchived  the is archived
     * @param image       the image
     * @param user        the user
     */
    public Tattoo(Long tattooId, String name, String description, double price, boolean isAllowed,
                  boolean isArchived, Image image, User user) {
        this.tattooId = tattooId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAllowed = isAllowed;
        this.isArchived = isArchived;
        this.image = image;
        this.user = user;
    }

    /**
     * Gets tattoo id.
     *
     * @return the tattoo id
     */
    public Long getTattooId() {
        return tattooId;
    }

    /**
     * Sets tattoo id.
     *
     * @param tattooId the tattoo id
     */
    public void setTattooId(Long tattooId) {
        this.tattooId = tattooId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
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
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
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
     * Gets allowed.
     *
     * @return the boolean
     */
    public boolean isAllowed() {
        return isAllowed;
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
     * Gets archived.
     *
     * @return the boolean
     */
    public boolean isArchived() {
        return isArchived;
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
     * Gets image.
     *
     * @return the image
     */
    public Image getImage() {
        return image;
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
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
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
        if (description != null ? !description.equals(tattoo.description) : tattoo.description != null) {
            return false;
        }
        if (image != null ? !image.equals(tattoo.image) : tattoo.image != null) {
            return false;
        }
        return user != null ? user.equals(tattoo.user) : tattoo.user == null;
    }

    @Override
    public int hashCode() {
        int hashCode;
        long temp;
        hashCode = tattooId != null ? tattooId.hashCode() : 0;
        hashCode = 31 * hashCode + (name != null ? name.hashCode() : 0);
        hashCode = 31 * hashCode + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        hashCode = 31 * hashCode + (int) (temp ^ (temp >>> 32));
        hashCode = 31 * hashCode + (isAllowed ? 1 : 0);
        hashCode = 31 * hashCode + (isArchived ? 1 : 0);
        hashCode = 31 * hashCode + (image != null ? image.hashCode() : 0);
        hashCode = 31 * hashCode + (user != null ? user.hashCode() : 0);
        return hashCode;
    }

    @Override
    public String toString() {
        final StringBuilder stringRepresentation = new StringBuilder("Tattoo{");
        stringRepresentation.append("tattooId=").append(tattooId);
        stringRepresentation.append(", name='").append(name).append('\'');
        stringRepresentation.append(", description='").append(description).append('\'');
        stringRepresentation.append(", price=").append(price);
        stringRepresentation.append(", isAllowed=").append(isAllowed);
        stringRepresentation.append(", isArchived=").append(isArchived);
        stringRepresentation.append(", image=").append(image);
        stringRepresentation.append(", user=").append(user);
        stringRepresentation.append('}');
        return stringRepresentation.toString();
    }
}
