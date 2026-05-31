package model;

public class Inventory {
    private Product product;
    private int balance;
    private double unitPrice;
    private double totalPrice;
    private int minStock;
    private int maxStock;

    public Inventory(Product product, int balance, double unitPrice, double totalPrice, int minStock, int maxStock) {
      this.product = product;
      this.balance = balance;
      this.unitPrice = unitPrice;
      this.totalPrice = totalPrice;
      this.minStock = minStock;
      this.maxStock = maxStock;
    }

    // Getter methods
    public Product getProduct() {
        return this.product;
    }

    public int getBalance() {
        return this.balance;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public int getMinStock() {
        return this.minStock;
    }

    public int getMaxStock() {
        return this.maxStock;
    }

    // Setter methods
    public void setProduct(Product product) {
        this.product = product;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }

    public void setMaxStock(int maxStock) {
        this.maxStock = maxStock;
    }    
}   