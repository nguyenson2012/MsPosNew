package project.mspos.entity;

import java.lang.reflect.Array;

public class ProductEntity {
    private int id;
    private String name;
    private String sku;
    private String image;
    private boolean hasOptions;
    private boolean isSalable;
    private boolean isAvailable;
    private int qty;
    private int numberProduct;
    private String shortDescription;
    private String description;
    private String images;
    private String additional;
    private float price;
    private float finalPrice;
    private String cateId;

    public ProductEntity() {}
    public  ProductEntity(int id, String name, String sku, String image, int qty, float price, Boolean hasOptions,Boolean isSalable,Boolean isAvailable, String additional, String images, String description) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.image = image;
        this.qty = qty;
        this.price = price;
        this.hasOptions = hasOptions;
        this.isSalable = isSalable;
        this.isAvailable = isAvailable;
        this.additional = additional;
        this.images = images;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isHasOptions() {
        return hasOptions;
    }

    public void setHasOptions(boolean hasOptions) {
        this.hasOptions = hasOptions;
    }

    public boolean isSalable() {
        return isSalable;
    }

    public void setSalable(boolean salable) {
        isSalable = salable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(float finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public int getNumberProduct() {
        return numberProduct;
    }

    public void setNumberProduct(int number) {
        this.numberProduct = number;
    }
}