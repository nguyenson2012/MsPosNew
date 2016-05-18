package project.mspos.entity;

/**
 * Created by SON on 4/6/2016.
 */
public class CustomSale {
    private String name;
    private boolean shippable;
    private float price;
    private int TaxClassId;
    private int quantity;

    public CustomSale(String name, boolean shippable, float price, int taxClassId, int quantity) {
        this.name = name;
        this.shippable = shippable;
        this.price = price;
        TaxClassId = taxClassId;
        this.quantity = quantity;
    }

    public CustomSale(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isShippable() {
        return shippable;
    }

    public void setShippable(boolean shippable) {
        this.shippable = shippable;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getTaxClassId() {
        return TaxClassId;
    }

    public void setTaxClassId(int taxClassId) {
        TaxClassId = taxClassId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
