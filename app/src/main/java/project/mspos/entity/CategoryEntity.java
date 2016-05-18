package project.mspos.entity;
import java.util.ArrayList;

public class CategoryEntity {

    private int id;
    private String name;
    private int level;
    private int parent;

    private ArrayList<ProductEntity> list_product;

    public CategoryEntity(int id, String name, int level, int parent) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.parent = parent;
    }
    public CategoryEntity() {
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public ArrayList<ProductEntity> getList_product() {
        return list_product;
    }

    public void setList_product(ArrayList<ProductEntity> list_product) {
        this.list_product = list_product;
    }
}
