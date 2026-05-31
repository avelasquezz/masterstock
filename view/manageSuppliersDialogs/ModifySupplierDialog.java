package view.manageSuppliersDialogs;

import service.ProductService;
import service.SupplierService;
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

public class ModifySupplierDialog extends JDialog {
    private SupplierService supplierService = SupplierService.getInstance();
    private ProductService productService = ProductService.getInstance();

    private JLabel idLabel;
    private JLabel idValueLabel;
    private JLabel nameTextFieldLabel;
    private JTextField nameTextField;
    private JLabel addressTextFieldLabel;
    private JTextField addressTextField;
    private JLabel phoneNumberTextFieldLabel;
    private JTextField phoneNumberTextField;
    private JButton acceptButton;
    private JLabel errorMessageLabel;

    public ModifySupplierDialog(JTable suppliersTable) {
        // Dialog config
        setTitle("Modify supplier");
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
        
        this.idValueLabel = new JLabel((String) suppliersTable.getValueAt(suppliersTable.getSelectedRow(), 0));
        this.idValueLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.idValueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.nameTextFieldLabel = new JLabel("Name");
        this.nameTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.nameTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.nameTextField = new JTextField((String) suppliersTable.getValueAt(suppliersTable.getSelectedRow(), 1));
        this.nameTextField.setFont(new Font("Arial", Font.PLAIN, 18));
        this.nameTextField.setMaximumSize(new Dimension(200, 40));
        this.nameTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        this.addressTextFieldLabel = new JLabel("Address");
        this.addressTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.addressTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.addressTextField = new JTextField((String) suppliersTable.getValueAt(suppliersTable.getSelectedRow(), 2));
        this.addressTextField.setFont(new Font("Arial", Font.PLAIN, 18));
        this.addressTextField.setMaximumSize(new Dimension(200, 40));
        this.addressTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        this.phoneNumberTextFieldLabel = new JLabel("Phone number");
        this.phoneNumberTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.phoneNumberTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.phoneNumberTextField = new JTextField((String) suppliersTable.getValueAt(suppliersTable.getSelectedRow(), 3));
        this.phoneNumberTextField.setFont(new Font("Arial", Font.PLAIN, 18));
        this.phoneNumberTextField.setMaximumSize(new Dimension(200, 40));
        this.phoneNumberTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        this.acceptButton = new JButton("Accept");
        this.acceptButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.acceptButton.setContentAreaFilled(true); 
    	this.acceptButton.setBorderPainted(false); 
    	this.acceptButton.setFocusPainted(false); 
        this.acceptButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.acceptButton.setForeground(Color.WHITE);
        this.acceptButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.errorMessageLabel = new JLabel("");
        this.errorMessageLabel.setFont(new Font("Arial", Font.BOLD, 16));
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
        dialogPanel.add(this.addressTextFieldLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.addressTextField);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add(this.phoneNumberTextFieldLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.phoneNumberTextField);
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
                    int newSupplierId = Integer.parseInt(ModifySupplierDialog.this.idValueLabel.getText());
                    String newSupplierName = ModifySupplierDialog.this.nameTextField.getText();
                    String newSupplierAddress = ModifySupplierDialog.this.addressTextField.getText();
                    String newSupplierPhoneNumber = ModifySupplierDialog.this.phoneNumberTextField.getText();
            
                    Supplier modifiedSupplier = new Supplier(newSupplierId, newSupplierName, newSupplierAddress, newSupplierPhoneNumber);
                    
                    ModifySupplierDialog.this.supplierService.getSupplierRepository().updateSupplier(modifiedSupplier);
                    ModifySupplierDialog.this.supplierService.updateTable((DefaultTableModel) suppliersTable.getModel());
                    
                    ModifySupplierDialog.this.productService.updateProductsSupplier(modifiedSupplier);

                    dispose();
                } catch (NumberFormatException ex) {
					ModifySupplierDialog.this.errorMessageLabel.setText("Invalid data was detected.");
				}
            }
        });
    }

    public void showDialog() {
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
