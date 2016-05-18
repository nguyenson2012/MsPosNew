package project.mspos.entity;

/**
 * Created by SON on 4/6/2016.
 */
public class DiscountEntity {
    private String nameDiscount;
    private DiscountType discountType;
    private float amount;
    private String discountCode;

    public DiscountEntity(){

    }

    public DiscountEntity(String nameDiscount, DiscountType discountType, float amount, String discountCode) {
        this.nameDiscount = nameDiscount;
        this.discountType = discountType;
        this.amount = amount;
        this.discountCode = discountCode;
    }

    public String getNameDiscount() {
        return nameDiscount;
    }

    public void setNameDiscount(String nameDiscount) {
        this.nameDiscount = nameDiscount;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }
}
