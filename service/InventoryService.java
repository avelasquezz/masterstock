package service;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import model.Movement;
import model.Inventory;
import model.Product;
import repository.InventoryRepository;

public class InventoryService {
    private InventoryService() {}
    private static InventoryService instance;

    public static InventoryService getInstance() {
        if (instance == null) {
            instance = new InventoryService();
        }
        return instance;
    }

    private InventoryRepository inventoryRepository = InventoryRepository.getInstance();

    public InventoryRepository getInventoryRepository() {
        return this.inventoryRepository;
    }

    public void updateTable(DefaultTableModel inventoryTableModel) {
        inventoryTableModel.setRowCount(0);

        DecimalFormat df = new DecimalFormat("#.##");

        for (Inventory inventory : inventoryRepository.getInventoryList()) {
            String[] tableRow = {
                String.valueOf(inventory.getProduct().getId()),
                inventory.getProduct().getName(), 
                String.valueOf(inventory.getBalance()),
                String.valueOf(df.format(inventory.getUnitPrice())),
                String.valueOf(df.format(inventory.getTotalPrice()))
            };
            inventoryTableModel.addRow(tableRow);
        }

        inventoryTableModel.fireTableDataChanged();
    }

    public int calculateBalance(Product product) {
        MovementService movementService = MovementService.getInstance();

        int balance = 0;
        List<Movement> movementsList = movementService.getMovementRepository().getMovementsList();
        
        for (Movement movement : movementsList) {
            if (movement.getProduct().getId() == product.getId() && movement.getType() == "Purchase") {
                balance = balance + movement.getQuantity(); 
            } else {
                if (movement.getProduct().getId() == product.getId() && movement.getType() == "Sale") {
                    balance = balance - movement.getQuantity();
                }
            }
        }

        return balance;
    }

    public int calculateCumulativePurchases(Product product) {
        MovementService movementService = MovementService.getInstance();

        int cumulativePurchases = 0;
        List<Movement> movementsList = movementService.getMovementRepository().getMovementsList();
        
        for (Movement movement : movementsList) {
            if (movement.getProduct().getId() == product.getId() && movement.getType() == "Purchase") {
                cumulativePurchases = cumulativePurchases + movement.getQuantity(); 
            }
        }

        return cumulativePurchases;
    }

    public double calculateUnitPrice(Product product) {
        MovementService movementService = MovementService.getInstance();

        double unitPrice = 0;
        List<Movement> movementsList = movementService.getMovementRepository().getMovementsList();
        
        int cumulativePurchases = calculateCumulativePurchases(product);

        for(Movement movement : movementsList) {
            if (movement.getProduct().getId() == product.getId() && movement.getType() == "Purchase") {
                unitPrice = unitPrice + ((movement.getQuantity() * movement.getUnitPrice()) / cumulativePurchases);
            }
        }

        return unitPrice;
    }

    public double calculateTotalPrice(Product product) {
        MovementService movementService = MovementService.getInstance();

        int balance = calculateBalance(product);
        double unitPrice = calculateUnitPrice(product);

        return balance * unitPrice;
    }

    public void exportAsCSV(JTable inventoryTable) throws IOException {
        JFileChooser fileChooser = new JFileChooser();

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            if (fileToSave != null) {
                String fileName = fileToSave.getAbsolutePath();

                if (!fileName.endsWith(".csv")) {
                    fileName += ".csv";
                }
                
                try (FileWriter writer = new FileWriter(fileName)) { 
                    for (int i = 0; i < inventoryTable.getColumnCount(); i++) {
                        writer.write("\"" + inventoryTable.getColumnName(i) + "\"");
                        if (i < inventoryTable.getColumnCount() - 1) {
                            writer.write(",");
                        }
                    }
                    writer.write("\n");

                    for (int i = 0; i < inventoryTable.getRowCount(); i++) {
                        for (int j = 0; j < inventoryTable.getColumnCount(); j++) {
                            writer.write("\"" + inventoryTable.getValueAt(i, j).toString()+ "\"");
                            if (j < inventoryTable.getColumnCount() - 1) {
                                writer.write(",");
                            }
                        }
                        writer.write("\n");
                    }
                }
            }
        }
    }
}
