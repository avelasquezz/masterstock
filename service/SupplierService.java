package service;

import model.Supplier;
import repository.SupplierRepository;

import java.util.List;
import java.util.Random;

import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

public class SupplierService {
    private SupplierService() {}
    private static SupplierService instance;

    public static SupplierService getInstance() {
        if (instance == null) {
            instance = new SupplierService();
        }
        return instance;
    }

    private SupplierRepository supplierRepository = SupplierRepository.getInstance();

    public SupplierRepository getSupplierRepository() {
        return this.supplierRepository;
    }

    public int generateId() {
        Random random = new Random();
        boolean findingNewId = true;
        int newId = 0;

        while(findingNewId) {
            findingNewId = false;
            newId = 1000 + random.nextInt(9000);
            for (Supplier supplier : this.supplierRepository.getSuppliersList()) {
                findingNewId = findingNewId || supplier.getId() == newId;
            }
        }

        return newId;
    }

    public List<String> getSupplierNamesList() {
        List<String> supplierNamesList = new ArrayList<>();
        
        for (Supplier supplier : supplierRepository.getSuppliersList()) {
            supplierNamesList.add(supplier.getName());
        }

        return supplierNamesList;
    }

    public void updateTable(DefaultTableModel suppliersTableModel) {
        suppliersTableModel.setRowCount(0);
        
        for (Supplier supplier : supplierRepository.getSuppliersList()) {
            String[] tableRow = {
                String.valueOf(supplier.getId()), 
                supplier.getName(), 
                supplier.getAddress(), 
                supplier.getPhoneNumber(), 
            };
            suppliersTableModel.addRow(tableRow);
        }

        suppliersTableModel.fireTableDataChanged();
    }
}
