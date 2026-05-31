package view.seeInventoryDialogs;

import model.Inventory;
import model.Product;
import service.InventoryService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetLimitsDialog extends JDialog {
    private InventoryService inventoryService = InventoryService.getInstance();
    
    private JLabel minStockTextFieldLabel;
    private JTextField minStockTextField;
    private JLabel maxStockTextFieldLabel;
    private JTextField maxStockTextField;
    private JButton acceptButton;
    private JLabel errorMessageLabel;

    public SetLimitsDialog(Inventory inventory) {
        // Dialog config
        setTitle("Set limits");
        setSize(300, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // UI components
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialogPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        
        this.minStockTextFieldLabel = new JLabel("Minimum stock");
        this.minStockTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.minStockTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.minStockTextField = new JTextField(String.valueOf(inventory.getMinStock()));
        this.minStockTextField.setFont(new Font("Arial", Font.PLAIN, 18));
        this.minStockTextField.setMaximumSize(new Dimension(200, 40));
        this.minStockTextField.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.maxStockTextFieldLabel = new JLabel("Maximum stock");
        this.maxStockTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.maxStockTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.maxStockTextField = new JTextField(String.valueOf(inventory.getMaxStock()));
        this.maxStockTextField.setFont(new Font("Arial", Font.PLAIN, 18));
        this.maxStockTextField.setMaximumSize(new Dimension(200, 40));
        this.maxStockTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
        
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
        
        dialogPanel.add(this.minStockTextFieldLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.minStockTextField);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add(this.maxStockTextFieldLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.maxStockTextField);
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
                    Product inventoryProduct = inventory.getProduct();
                    int inventoryBalance = inventory.getBalance();
                    double inventoryUnitPrice = inventory.getUnitPrice();
                    double inventoryTotalPrice = inventory.getTotalPrice();
                    int inventoryMinStock = Integer.parseInt(SetLimitsDialog.this.minStockTextField.getText());
                    int inventoryMaxStock = Integer.parseInt(SetLimitsDialog.this.maxStockTextField.getText());

                    Inventory newInventory = new Inventory(inventoryProduct, inventoryBalance, inventoryUnitPrice, inventoryTotalPrice, inventoryMinStock, inventoryMaxStock);

                    SetLimitsDialog.this.inventoryService.getInventoryRepository().updateInventory(newInventory);
                    
                    dispose();
                } catch (NumberFormatException ex) {
					SetLimitsDialog.this.errorMessageLabel.setText("Invalid data was detected.");
				}
            }
        });
    }

    public void showDialog() {
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
