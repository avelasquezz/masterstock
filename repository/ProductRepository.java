package repository;

import java.util.List;
import java.util.ArrayList;

import model.Product;

public class ProductRepository {
    private ProductRepository() {}
    private static ProductRepository instance;

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    private List<Product> productsList = new ArrayList<>();

    public Product searchProductById(int id) {
        for (Product product : productsList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public Product searchProductByName(String name) {
        for (Product product : productsList) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    public List<Product> searchProductsByName(String name) {
        List<Product> foundProducts = new ArrayList<>();
        for (Product product : productsList) {
            if (product.getName().toLowerCase().contains(name)) {
                foundProducts.add(product);
            }
        }
        return foundProducts;
    }

    public void addProduct(Product productToAdd) {
        productsList.add(productToAdd);
    }

    public void removeProduct(Product productToRemove) {
        productsList.remove(productToRemove);
    }

    public void updateProduct(Product modifiedProduct) {
        int modifiedProductId = modifiedProduct.getId();
        Product originalProduct = searchProductById(modifiedProductId);
        int originalProductIndex = productsList.indexOf(originalProduct);

        productsList.set(originalProductIndex, modifiedProduct);
    }

    public List<Product> getProductsList() {
        return new ArrayList<>(productsList);
    }
}
