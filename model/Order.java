package model;

import java.time.LocalDate;

public class Order {
    private int id;
    private LocalDate orderDate;
    private Product product;
    private Supplier supplier;
    private int quantity;
    private boolean state;
    private LocalDate receivedDate;

    public Order(int id, LocalDate orderDate, Product product, Supplier supplier, int quantity, boolean state, LocalDate receivedDate) {
        this.orderDate = orderDate;
        this.product = product;
        this.supplier = supplier;
        this.quantity = quantity;
        this.state = state;
        this.receivedDate = receivedDate;
        this.id = id;
    }

    // Getter methods
    public int getId() {
        return this.id;
    }
    
    public LocalDate getOrderDate() {
        return this.orderDate;
    }

    public Product getProduct() {
        return this.product;
    }

    public Supplier getSupplier() {
        return this.supplier;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public boolean getState() {
        return this.state;
    }

    public LocalDate getReceivedDate() {
        return this.receivedDate;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }
    
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }
}
