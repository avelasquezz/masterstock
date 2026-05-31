package model;

import java.time.LocalDate;

public class Movement {
    private int id;
    private LocalDate date;
    private String type;
    private int quantity;
    private double unitPrice;
    private String description;
    private Product product;

    public Movement(int id, LocalDate date, String type, int quantity, double unitPrice, String description, Product product) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.description = description;
        this.product = product;
    }

    // Getter methods
    public int getId() {
        return this.id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getType() {
        return this.type;
    }

    public int getQuantity() {
        return this.quantity;
    } 

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public String getDescription() {
        return this.description;
    }

    public Product getProduct() {
        return this.product;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    } 

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}