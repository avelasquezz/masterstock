package model;

public class Product {
    private int id;
    private String name;
    private String category;
    private Supplier supplier;

    public Product(int id, String name, String category, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.supplier = supplier;
    }

    // Getter methods
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public Supplier getSupplier() {
        return this.supplier;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}