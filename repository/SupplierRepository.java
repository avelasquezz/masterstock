package repository;

import java.util.List;
import java.util.ArrayList;

import model.Supplier;

public class SupplierRepository {
    private SupplierRepository() {}
    private static SupplierRepository instance;

    public static SupplierRepository getInstance() {
        if (instance == null) {
            instance = new SupplierRepository();
        }
        return instance;
    }

    private List<Supplier> suppliersList = new ArrayList<>();

    public Supplier searchSupplierById(int id) {
        for (Supplier supplier : suppliersList) {
            if (supplier.getId() == id) {
                return supplier;
            }
        }
        return null;
    }

    public Supplier searchSupplierByName(String name) {
        for (Supplier supplier : suppliersList) {
            if (supplier.getName().equals(name)) {
                return supplier;
            }
        }
        return null;
    }

    public void addSupplier(Supplier supplierToAdd) {
        suppliersList.add(supplierToAdd);
    }

    public void removeSupplier(Supplier supplierToRemove) {
        suppliersList.remove(supplierToRemove);
    }

    public void updateSupplier(Supplier modifiedSupplier) {
        int modifiedSupplierId = modifiedSupplier.getId();
        Supplier originalSupplier = searchSupplierById(modifiedSupplierId);
        int originalSupplierIndex = suppliersList.indexOf(originalSupplier);

        suppliersList.set(originalSupplierIndex, modifiedSupplier);
    }

    public List<Supplier> getSuppliersList() {
        return new ArrayList<>(suppliersList);
    }
}
