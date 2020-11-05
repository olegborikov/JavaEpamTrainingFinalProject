package com.borikov.bullfinch.model.entity;

/**
 * The {@code Image} class represents image entity.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class Image {
    /**
     * The value is used for image id storage.
     */
    private Long imageId;

    /**
     * The value is used for name storage.
     */
    private String name;

    /**
     * Instantiates a new Image.
     *
     * @param imageId the image id
     * @param name    the name
     */
    public Image(Long imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    /**
     * Gets image id.
     *
     * @return the image id
     */
    public Long getImageId() {
        return imageId;
    }

    /**
     * Sets image id.
     *
     * @param imageId the image id
     */
    public void setImageId(Long imageId) {
        this.imageId = imageId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Image image = (Image) o;
        if (imageId != null ? !imageId.equals(image.imageId) : image.imageId != null) {
            return false;
        }
        return name != null ? name.equals(image.name) : image.name == null;
    }

    @Override
    public int hashCode() {
        int result = imageId != null ? imageId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Image{");
        sb.append("imageId=").append(imageId);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
