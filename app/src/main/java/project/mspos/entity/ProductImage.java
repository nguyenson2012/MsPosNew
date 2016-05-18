package project.mspos.entity;

/**
 * Created by CONGHAO on 3/16/2016.
 */
public class ProductImage {
    private int mProductImageID;
    private int mProductImage;

    public ProductImage(int mProductImageID, int mProductImage) {
        this.mProductImageID = mProductImageID;
        this.mProductImage = mProductImage;
    }

    public int getmProductImageID() {
        return mProductImageID;
    }

    public void setmProductImageID(int mProductImageID) {
        this.mProductImageID = mProductImageID;
    }

    public int getmProductImage() {
        return mProductImage;
    }

    public void setmProductImage(int mProductImage) {
        this.mProductImage = mProductImage;
    }
}
