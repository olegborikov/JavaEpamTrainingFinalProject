package com.borikov.bullfinch.entity;

public class Image {
    private Long imageId;
    private String name;
    private Tattoo tattoo;

    public Image(Long imageId, String name, Tattoo tattoo) {
        this.imageId = imageId;
        this.name = name;
        this.tattoo = tattoo;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tattoo getTattoo() {
        return tattoo;
    }

    public void setTattoo(Tattoo tattoo) {
        this.tattoo = tattoo;
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
        if (name != null ? !name.equals(image.name) : image.name != null) {
            return false;
        }
        return tattoo != null ? tattoo.equals(image.tattoo) : image.tattoo == null;
    }

    @Override
    public int hashCode() {
        int result = imageId != null ? imageId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tattoo != null ? tattoo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Image{");
        sb.append("imageId=").append(imageId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", tattoo=").append(tattoo);
        sb.append('}');
        return sb.toString();
    }
}
