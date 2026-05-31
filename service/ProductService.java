package service;

import repository.ProductRepository;
import model.Product;
import model.Supplier;

import java.util.Random;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private ProductService() {}
    private static ProductService instance;

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    private ProductRepository productRepository = ProductRepository.getInstance();

    public ProductRepository getProductRepository() {
        return this.productRepository;
    }

    public int generateId() {
        Random random = new Random();
        boolean findingNewId = true;
        int newId = 0;

        while(findingNewId) {
            findingNewId = false;
            newId = 1000 + random.nextInt(9000);
            for (Product product : this.productRepository.getProductsList()) {
                findingNewId = findingNewId || product.getId() == newId;
            }
        }

        return newId;
    }

    public List<String> getProductNamesList() {
        List<String> productNamesList = new ArrayList<>();
        
        for (Product product : productRepository.getProductsList()) {
            productNamesList.add(product.getName());
        }

        return productNamesList;
    }

    public void updateTable(DefaultTableModel productsTableModel) {
        productsTableModel.setRowCount(0);
        
        for (Product product : productRepository.getProductsList()) {
            String[] tableRow = {
                String.valueOf(product.getId()), 
                product.getName(), product.getCategory(), 
                product.getSupplier().getName()
            };
            productsTableModel.addRow(tableRow);
        }

        productsTableModel.fireTableDataChanged();
    }

    public void updateTable(DefaultTableModel productsTableModel, List<Product> productsList) {
        productsTableModel.setRowCount(0);
        
        for (Product product : productsList) {
            String[] tableRow = {
                String.valueOf(product.getId()), 
                product.getName(), product.getCategory(), 
                product.getSupplier().getName()
            };
            productsTableModel.addRow(tableRow);
        }

        productsTableModel.fireTableDataChanged();
    }

    public void updateProductsSupplier(Supplier supplier) {
        List<Product> productsList = productRepository.getProductsList();
        
        for (Product product : productsList) {
            if (product.getSupplier().getId() == supplier.getId()) {
                Product modifiedProduct = new Product(product.getId(), product.getName(), product.getCategory(), supplier);
                productRepository.updateProduct(modifiedProduct);
            }
        }
    }
}
