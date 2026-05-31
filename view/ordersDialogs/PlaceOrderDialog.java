package view.ordersDialogs;

import service.*;
import model.Product;
import model.Supplier;
import model.Order;
import model.Inventory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class PlaceOrderDialog extends JDialog {
    private ProductService productService = ProductService.getInstance();
    private OrderService orderService = OrderService.getInstance();
    private InventoryService inventoryService = InventoryService.getInstance();
    
    private JLabel idLabel;
    private JLabel idValueLabel;
    private JLabel productComboBoxLabel;
    private JComboBox<String> productComboBox;
    private JLabel quantityTextFieldLabel;
    private JTextField quantityTextField;
    private JButton acceptButton;
    private JLabel errorMessageLabel;

    public PlaceOrderDialog(JTable ordersTable) {
        // Dialog config
        setTitle("Place order");
        setSize(380, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // UI components
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialogPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        
        this.idLabel = new JLabel("ID");
        this.idLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.idValueLabel = new JLabel(String.valueOf(this.orderService.generateId()));
        this.idValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        this.idValueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.productComboBoxLabel = new JLabel("Product");
        this.productComboBoxLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.productComboBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.productComboBox = new JComboBox<>(this.productService.getProductNamesList().toArray(new String[0]));
        this.productComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        this.productComboBox.setMaximumSize(new Dimension(200, 40));

        this.quantityTextFieldLabel = new JLabel("Amount");
        this.quantityTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.quantityTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.quantityTextField = new JTextField();
        this.quantityTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.quantityTextField.setMaximumSize(new Dimension(200, 40));
        this.quantityTextField.setBorder(new EmptyBorder(10, 10, 10, 10));

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
        dialogPanel.add (this.productComboBoxLabel);
        dialogPanel.add (this.productComboBox);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add(this.quantityTextFieldLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.quantityTextField);
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
                    if (PlaceOrderDialog.this.productService.getProductRepository().getProductsList().size() == 0) {
                        PlaceOrderDialog.this.errorMessageLabel.setText("There are not registered products.");
                        return;
                    } else {
                        int newOrderId = Integer.parseInt(PlaceOrderDialog.this.idValueLabel.getText());
                        LocalDate newOrderDate = LocalDate.now();
                        String newOrderProductName = (String) PlaceOrderDialog.this.productComboBox.getSelectedItem();
                        Product newOrderProduct = PlaceOrderDialog.this.productService.getProductRepository().searchProductByName(newOrderProductName);
                        Supplier newOrdeSupplier = newOrderProduct.getSupplier();
                        int newOrderQuantity = Integer.valueOf(PlaceOrderDialog.this.quantityTextField.getText());
                        boolean newOrderState = false;
                        LocalDate newOrderReceivedDate = null;

                        Inventory inventory = PlaceOrderDialog.this.inventoryService.getInventoryRepository().searchInventoryByProduct(newOrderProduct);

                        if ((inventory.getBalance() + newOrderQuantity) > inventory.getMaxStock()) {
                            PlaceOrderDialog.this.errorMessageLabel.setText("The order amount exceed the maximum stock.");
                            return;
                        } 

                        Order newOrder = new Order(newOrderId, newOrderDate, newOrderProduct, newOrdeSupplier, newOrderQuantity, newOrderState, newOrderReceivedDate);

                        PlaceOrderDialog.this.orderService.getOrderRepository().addOrder(newOrder);
                        PlaceOrderDialog.this.orderService.updateTable((DefaultTableModel) ordersTable.getModel());

                        dispose();
                    }
                } catch (NumberFormatException ex) {
					PlaceOrderDialog.this.errorMessageLabel.setText("Invalid data was detected.");
				}
            }
        });
    }

    public void showDialog() {
        setLocationRelativeTo(null);
        setVisible(true);
    }
}