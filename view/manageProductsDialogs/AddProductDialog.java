package view.manageProductsDialogs;

import service.*;
import model.Inventory;
import model.Product;
import model.Supplier;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductDialog extends JDialog {
    private ProductService productService = ProductService.getInstance();
    private SupplierService supplierService = SupplierService.getInstance();
    private InventoryService inventoryService = InventoryService.getInstance();
    
    private JLabel idLabel;
    private JLabel idValueLabel;
    private JLabel nameTextFieldLabel;
    private JTextField nameTextField;
    private JLabel categoryTextFieldLabel;
    private JTextField categoryTextField;
    private JLabel supplierComboBoxLabel;
    private JComboBox<String> supplierComboBox;
    private JButton acceptButton;
    private JLabel errorMessageLabel;

    public AddProductDialog(JTable productsTable) {
        // Dialog config
        setTitle("Add product");
        setSize(300, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // UI components
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialogPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        
        this.idLabel = new JLabel("ID");
        this.idLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.idValueLabel = new JLabel(String.valueOf(this.productService.generateId()));
        this.idValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        this.idValueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.nameTextFieldLabel = new JLabel("Name");
        this.nameTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.nameTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.nameTextField = new JTextField();
        this.nameTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.nameTextField.setMaximumSize(new Dimension(200, 40));
        this.nameTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        this.categoryTextFieldLabel = new JLabel("Category");
        this.categoryTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.categoryTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.categoryTextField = new JTextField();
        this.categoryTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.categoryTextField.setMaximumSize(new Dimension(200, 40));
        this.categoryTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        this.supplierComboBoxLabel = new JLabel("Supplier");
        this.supplierComboBoxLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.supplierComboBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.supplierComboBox = new JComboBox<>(this.supplierService.getSupplierNamesList().toArray(new String[0]));
        this.supplierComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        this.supplierComboBox.setMaximumSize(new Dimension(200, 40));

        this.acceptButton = new JButton("Accept");
        this.acceptButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.acceptButton.setContentAreaFilled(true); 
    	this.acceptButton.setBorderPainted(false); 
    	this.acceptButton.setFocusPainted(false); 
        this.acceptButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.acceptButton.setForeground(Color.WHITE);
        this.acceptButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.errorMessageLabel = new JLabel("");
        this.errorMessageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.errorMessageLabel.setForeground(new Color(235, 87, 87)); // Set red color
        this.errorMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to panel
        dialogPanel.add(Box.createVerticalGlue());
        
        dialogPanel.add(this.idLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.idValueLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add(this.nameTextFieldLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.nameTextField);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add(this.categoryTextFieldLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.categoryTextField);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add (this.supplierComboBoxLabel);
        dialogPanel.add (this.supplierComboBox);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add(this.acceptButton);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.errorMessageLabel);
        
        dialogPanel.add(Box.createVerticalGlue());

        add(dialogPanel);

        // Button actions
        acceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (AddProductDialog.this.supplierService.getSupplierRepository().getSuppliersList().size() == 0) {
                        AddProductDialog.this.errorMessageLabel.setText("There are not registered suppliers.");
                        return;
                    } else {
                        int newProductId = Integer.parseInt(AddProductDialog.this.idValueLabel.getText());
                        String newProductName = AddProductDialog.this.nameTextField.getText();
                        String newProductCategory = AddProductDialog.this.categoryTextField.getText();

                        String supplierName = (String) AddProductDialog.this.supplierComboBox.getSelectedItem();
                        Supplier newProductSupplier = AddProductDialog.this.supplierService.getSupplierRepository().searchSupplierByName(supplierName);

                        Product newProduct = new Product(newProductId, newProductName, newProductCategory, newProductSupplier);

                        AddProductDialog.this.productService.getProductRepository().addProduct(newProduct);
                        AddProductDialog.this.productService.updateTable((DefaultTableModel) productsTable.getModel());

                        Inventory newInventory = new Inventory(newProduct, 0, 0, 0, 10, 150);

                        AddProductDialog.this.inventoryService.getInventoryRepository().addInventory(newInventory);

                        dispose();
                    }
                } catch (NumberFormatException ex) {
					AddProductDialog.this.errorMessageLabel.setText("Invalid data was detected.");
				}
            }
        });
    }

    public void showDialog() {
        setLocationRelativeTo(null);
        setVisible(true);
    }
}